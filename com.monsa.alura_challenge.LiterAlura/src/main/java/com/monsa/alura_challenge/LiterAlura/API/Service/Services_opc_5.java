package com.monsa.alura_challenge.LiterAlura.API.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_autores;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_libros;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Libro_repository;
import com.monsa.alura_challenge.LiterAlura.API.UI.Console_cmd;
import com.monsa.alura_challenge.LiterAlura.API.UI.Mensaje;
import com.monsa.alura_challenge.LiterAlura.API.UI.Menu;

import jakarta.transaction.Transactional;

@Service
public class Services_opc_5 {

    private final Libro_repository libro_repository;

    public Services_opc_5(Libro_repository libro_repository) { 
        this.libro_repository = libro_repository; 
    }

    /**
     * Consulta Base de datos y obtiene información
     * 
     * Este método consulta la Base de Datos del proyecto y obtiene información de todos
     * los libros registrados hasta el momento de la consulta, todos ellos por un idioma
     * específico.
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public void ListarLibrosPorIdioma(Scanner scan) throws IOException, InterruptedException {

        Console_cmd.Clear_cmd();
        Menu.Menu_opc_5();
       
        System.out.println("""
                \nAlgunos idiomas disponibles. Referencias tabla de códigos ISO 639-1

                Inglés("en"), Chino Mandarín("zh"), Español("es"), Frances("fr")
                Hindi("hi"), Árabe("ar"), Portugués("pt"), Alemán("de"), Ruso("ru")
                Bengalí("bn"), Urdu("ur"), Japonés("ja"), Coreano("ko"), Gales("cy")\n"""); 

        String libro_code = null;

        do {
            Mensaje.ColorAmarillo("Escriba el código del idioma por el que desea buscar: ");
            libro_code = scan.nextLine().trim().toLowerCase();
            Mensaje.ColorVerde("Buscando...");

            if (libro_code.matches("[A-Za-z]{2}")) {
                break;
            } else {
                Mensaje.ColorRojo("Introduce únicamente dos letras en este campo. ¡Inténtalo de nuevo!");
            }
        } while (true);

        printLibroIdioma(libro_code);

        Menu.Menu_opc_5_cierre();;
        Mensaje.ColorAmarillo("Presione 'enter' para regresar el menú anterior...");
        scan.nextLine();
        Console_cmd.Clear_cmd();
        
    }

     /**
     * Imprime con formato la información del libro.
     *
     * Este método imprime con formato la información registrada del libro
     * para suministar una vista amigable de información para el usuario
     *
     * @param autoresEnDB Datos de tabla autores respuesta de la base de datos.
     * 
     */
    private void printLibroIdioma(String libro_code) {

        final String ANSI_Reset = "\u001B[0m";
        final String ANSI_Verde = "\u001B[32m";  
        final String ANSI_Morado = "\u001B[35m";
        String color = ANSI_Verde;

        List<JPA_libros> librosEnDBxIdioma = new ArrayList<>();
        
        librosEnDBxIdioma = libro_repository.findByIdioma(libro_code);

        if (librosEnDBxIdioma.size() == 0) {
            Mensaje.ColorMorado("\nNo se encontraron libros con ese idioma en la base de datos...\n");
        } else {
           
            System.out.printf("\nTotal de resultados: %d libros en %s\n", librosEnDBxIdioma.size(), Codigo_idioma.fromCodigo(libro_code));
           
            Menu.Menu_inter_libro();

            for (int i = 0; i < librosEnDBxIdioma.size(); i++) {

                JPA_libros libro = librosEnDBxIdioma.get(i);

                System.out.printf(color + "\n>>> id: %s <<<" + ANSI_Reset + "\nTítulo: %s\n", libro.getIdgutendex(), libro.getTitulo());
                String autoresNombres = libro.getAutores().stream()
                                                       .map(JPA_autores::getNombre)
                                                       .collect(Collectors.joining(" - "));
                System.out.printf("Autor(es): %s \nTotal de descargas: %s \n", autoresNombres, libro.getDescargas());
                System.out.printf("URL de lectura: %s \n", libro.getUrlString());

                if (i%2 == 0) {
                    color = ANSI_Morado;
                } else {
                    color = ANSI_Verde;
                }
            }
        
        }

    }

}

   
    



