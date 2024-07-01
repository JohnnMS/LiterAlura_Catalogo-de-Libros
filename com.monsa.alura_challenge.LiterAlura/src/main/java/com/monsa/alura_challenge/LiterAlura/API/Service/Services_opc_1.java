package com.monsa.alura_challenge.LiterAlura.API.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.monsa.alura_challenge.LiterAlura.API.Controllers.ConsultarAPI;
import com.monsa.alura_challenge.LiterAlura.API.Models.DTOs.DTO_consulta_libros;
import com.monsa.alura_challenge.LiterAlura.API.Models.DTOs.DTO_libros;
import com.monsa.alura_challenge.LiterAlura.API.Models.DTOs.DTO_libros_persona;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_autores;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_idiomas;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_libros;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Autores_repository;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Idiomas_repository;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Libro_repository;
import com.monsa.alura_challenge.LiterAlura.API.UI.Console_cmd;
import com.monsa.alura_challenge.LiterAlura.API.UI.Mensaje;
import com.monsa.alura_challenge.LiterAlura.API.UI.Menu;

import jakarta.transaction.Transactional;

@Service
public class Services_opc_1 {

    private final ConvierteDatos conversor = new ConvierteDatos();
    private final Libro_repository libro_repository;
    private final Idiomas_repository idiomas_repository;
    private final Autores_repository autores_repository;

    public Services_opc_1(Libro_repository libro_repository,Idiomas_repository idiomas_repository,Autores_repository autores_repository) { 
        this.libro_repository = libro_repository;
        this.idiomas_repository = idiomas_repository;
        this.autores_repository = autores_repository;
    }
    /**
     * Consulta API y obtiene información
     * 
     * Este método consulta la API del proyecto Gutendex y obtiene información del libro 
     * cuyo titulo, nombre o número Id es proporcionado.
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public void BuscarLibroPorNombre(Scanner scan) throws IOException, InterruptedException {

        do {
            Console_cmd.Clear_cmd();
            Menu.Menu_opc_1();
            Mensaje.ColorAmarillo("\nEscriba el título/id del libro o 'salir' para regresar al menú:");
            String libroBuscado = scan.nextLine().trim();
    
            if (libroBuscado == "") {

                Mensaje.ColorRojo("Introduce una opción válida, presiona 'enter'. ¡Inténtalo de nuevo!");
                scan.nextLine();
                
            } else {

                if (libroBuscado.equalsIgnoreCase("salir")) {
                    break;
                } else {
                    Mensaje.ColorVerde("Buscando...");
                }
    
                var json = ConsultarAPI.obtenerDatos(libroBuscado);
                var consulta_libro = conversor.obtenerDatos(json, DTO_consulta_libros.class);
                
                System.out.printf("\nTotal de resultados: %s\n", consulta_libro.libros().size());
    
                Menu.Menu_inter_libro();
                //Publico todos los libros encontrados
                printLibro(consulta_libro);
    
                Menu.Menu_opc_1_cierre();
    
                String opc_final;
                do {
                    Mensaje.ColorAmarillo("""
                        ¿Desea guardar algún libro en la base de datos?

                        -¿Sí? Escriba el 'id' del ejemplar...
                        -¿No? Escriba 'no' si desea realizar otra búsqueda...
                        -Escriba 'salir' si desea regresar al menú principal...
                        """);
                        
                    opc_final = scan.nextLine().toLowerCase();
    
                    if (opc_final.equalsIgnoreCase("salir") || opc_final.equalsIgnoreCase("no")) {
                        break;
                    }
    
                    if (opc_final.matches("\\d+")) {
                        
                        json = ConsultarAPI.obtenerDatos(opc_final);
                        consulta_libro = conversor.obtenerDatos(json, DTO_consulta_libros.class);
                        
                        printLibro(consulta_libro);

                        if (json.isEmpty() || consulta_libro == null) {
                            Mensaje.ColorRojo("Hubo un error al consultar la información del libro...");
                        } else {
                            
                            //Validar exitencia de libro antes de guardar
                            if (obtenerIdxLibro(consulta_libro)) {
                                //Obtener id o guardar idiomas
                                var idiomas_Id = obtenerIdorSaveIdioma(consulta_libro);
                                //Obtener id o guardar autores
                                var autores = obtenerIdorSaveAutores(consulta_libro);
                                //guardar libro
                                saveLibroEnDB(consulta_libro, idiomas_Id, autores);

                            } else {
                                Mensaje.ColorMorado("El libro ya se encuentra en la base de datos...\nPresiona 'enter'");
                                scan.nextLine();
                            }
                
                        }
                        
                    } else {
                        Mensaje.ColorRojo("Introduce una opción válida. ¡Inténtalo de nuevo!");
                    }
                    
                } while (true);
                if (opc_final.equalsIgnoreCase("salir")) {break;}
                if (opc_final.equalsIgnoreCase("no")) {continue;}
            }
        } while (true);  
        Console_cmd.Clear_cmd();
    }
    /**
     * Consulta el Id del libro que será guardado.
     *
     * Este método realiza una consulta a la base de datos para encontrar el Id del libro
     * cuyo título coincida con id proporcionado. Si no se encuentra el Id del libro,
     * retorna TRUE, para proceder a guardar el libro.
     *
     * @param consulta_libro Datos del libro respuesta de la API.
     * @return TRUE si no encuentra el Id, o FALSE si ocurre un error al consultar ese Id o haya el Id.
     * 
     */
    private boolean obtenerIdxLibro(DTO_consulta_libros consulta_libro) {

        try {

            JPA_libros idx = new JPA_libros();
            String id_buscado = consulta_libro.libros().get(0).id();
            idx = libro_repository.findIdByIdgutendex(id_buscado);
            if (idx == null) {
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            Mensaje.ColorRojo("Hubo un error al intentar buscar el libro...\nError: " + e);
            return false;
        }
    
    }
    /**
     * Consulta o guarda el Id idioma del libro.
     *
     * Este método realiza una consulta a la base de datos para encontrar el Id del libro
     * cuyo título coincida con el nombre proporcionado. Si no se encuentra el Id del libro,
     * lo crea o retorna null.
     *
     * @param consulta_libro Datos del libro respuesta de la API.
     * @return El Id encontrado, o null si ocurre un error al consultar ese Id.
     * 
     */
    @Transactional
    private List<JPA_idiomas> obtenerIdorSaveIdioma(DTO_consulta_libros consulta_libro) {

        try {

            List<JPA_idiomas> idiomas_Id = new ArrayList<>();
            for (String idiomaNombre : consulta_libro.libros().get(0).lenguaje()) {
                JPA_idiomas idioma = idiomas_repository.findIdByIdioma(idiomaNombre);
                if (idioma == null) {
                    idioma = new JPA_idiomas();
                    idioma.setIdioma(idiomaNombre);
                    idioma = idiomas_repository.save(idioma);
                } 
                idiomas_Id.add(idioma);
            }
            return idiomas_Id;

        } catch (Exception e) {
            Mensaje.ColorRojo("Hubo un error al intentar guardar o buscar el idioma del libro...\nError: " + e);
            return null;
        }

    }
    /**
     * Consulta o guarda el Id del autor junto con la información del autor del libro.
     *
     * Este método realiza una consulta a la base de datos para encontrar el Id del autor del libro
     * cuyo título coincida con el nombre proporcionado. Si no se encuentra el Id del autor del libro,
     * lo crea y guarda toda la información del autor o retorna null.
     *
     * @param consulta_libro Datos del libro respuesta de la API.
     * @return El Id encontrado, o null si ocurre un error al consultar ese Id.
     * 
     */
    @Transactional
    private List<JPA_autores> obtenerIdorSaveAutores(DTO_consulta_libros consulta_libro) {

        try {

            List<JPA_autores> autores = new ArrayList<>();
            for (DTO_libros_persona DTOautor : consulta_libro.libros().get(0).autores()) {
                JPA_autores autor = autores_repository.findIdByNombreAndFechaNacimiento(DTOautor.nombre(), DTOautor.fecha_nacimiento());         
                if (autor == null) {
                    if (DTOautor.nombre().isEmpty()) {
                        autor = autores_repository.findIdByNombreAndFechaNacimiento("Desconocido","Desconocida");
                        if (autor == null) {
                            autor = new JPA_autores();
                            autor.setNombre("Desconocido");
                            autor.setFechaNacimiento("Desconocida");
                            autor.setFechaFallecimiento("Desconocida");
                            autor = autores_repository.save(autor);
                        }
                    } else {
                        autor = new JPA_autores();
                        autor.setNombre(DTOautor.nombre());
                        if (DTOautor.fecha_nacimiento() == null || DTOautor.fecha_nacimiento().isBlank()) {
                            autor.setFechaNacimiento("Desconocida");
                        } else {
                            autor.setFechaNacimiento(DTOautor.fecha_nacimiento());
                        }
                        if (DTOautor.fecha_fallecimiento() == null || DTOautor.fecha_fallecimiento().isBlank()) {
                            autor.setFechaFallecimiento("Desconocida");
                        } else {
                            autor.setFechaFallecimiento(DTOautor.fecha_fallecimiento()); 
                        }                  
                        autor = autores_repository.save(autor);
                    }
                }
                autores.add(autor);
            }
            return autores;

        } catch (Exception e) {
            Mensaje.ColorRojo("Hubo un error al intentar guardar o buscar el autor del libro...\nError: " + e);
            return null;
        }
      
    }
    /**
     * Guarda la informacion consultada del libro.
     *
     * Este método guarda en la base de datos la información suministrada de el libro.
     *
     * @param consulta_libro Datos del libro respuesta de la API.
     * @param idiomas_Id Datos Id para el idioma del libro.
     * @param autores Datos Id para el autor del libro.
     * @return El Id encontrado, o null si ocurre un error al consultar ese Id.
     * 
     */
    @Transactional
    private void saveLibroEnDB(DTO_consulta_libros consulta_libro, List<JPA_idiomas> idiomas_Id, List<JPA_autores> autores) {
        
        try {
            
            JPA_libros libroGuardado = new JPA_libros();
            libroGuardado.setIdgutendex(consulta_libro.libros().get(0).id());
            libroGuardado.setTitulo(consulta_libro.libros().get(0).titulo());

            if (consulta_libro.libros().get(0).formato().urlString() == null) {

                if (consulta_libro.libros().get(0).formato().pdfString() == null) {
                    libroGuardado.setUrlString("No disponible");
                } else {
                    libroGuardado.setUrlString(consulta_libro.libros().get(0).formato().pdfString());
                }

            } else {
                libroGuardado.setUrlString(consulta_libro.libros().get(0).formato().urlString());
            }

            if (consulta_libro.libros().get(0).conteo_descargas() == null) {
                libroGuardado.setDescargas("No disponible");
            } else {
                libroGuardado.setDescargas(consulta_libro.libros().get(0).conteo_descargas());
            }
           
            libroGuardado.setIdiomas(idiomas_Id);

            if (autores.isEmpty()) {
                List<JPA_autores> autor_final = new ArrayList<>();
                var autor = new JPA_autores();
                autor.setNombre("Desconocido");
                autor.setFechaNacimiento("Desconocida");
                autor.setFechaFallecimiento("Desconocida");
                autor_final.add(autor);
                libroGuardado.setAutores(autor_final);
            } else {
                libroGuardado.setAutores(autores); 
            }

            libro_repository.save(libroGuardado);

            Mensaje.ColorVerde("¡Libro guardado con éxito!\n");
            
        } catch (Exception e) {
            Mensaje.ColorRojo("Hubo un error al intentar guardar el libro...\nError: " + e);
        }

    }
    /**
     * Imprime con formato la información del libro.
     *
     * Este método imprime con formato la información registrada del libro
     * para suministar una vista amigable de información para el usuario
     *
     * @param consulta_libro Datos del libro respuesta de la API.
     * 
     */
    private void printLibro(DTO_consulta_libros consulta_libro) {

        final String ANSI_Reset = "\u001B[0m";
        final String ANSI_Verde = "\u001B[32m";  
        final String ANSI_Morado = "\u001B[35m";

        List<DTO_libros> libros_array = consulta_libro.libros();
        String color = ANSI_Verde;
    
        for (int i = 0; i < libros_array.size(); i++) {
            DTO_libros libro = libros_array.get(i);
            System.out.printf(color + "\n>>> id: %s <<<" + ANSI_Reset + "\nTítulo: %s\n", libro.id(), libro.titulo());
            String autoresNombres = libro.autores().stream()
                                                   .map(DTO_libros_persona::nombre)
                                                   .collect(Collectors.joining(" - "));
            System.out.printf("Autor: %s \nTotal de descargas: %s \n", autoresNombres, libro.conteo_descargas());
            System.out.printf("URL de lectura: %s \n", (libro.formato().urlString() == null)? libro.formato().pdfString() : libro.formato().urlString());
            if (i%2 == 0) {
                color = ANSI_Morado;
            } else {
                color = ANSI_Verde;
            }
        }

    }

}
