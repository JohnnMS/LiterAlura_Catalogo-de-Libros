package com.monsa.alura_challenge.LiterAlura.API.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_autores;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_idiomas;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_libros;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Libro_repository;
import com.monsa.alura_challenge.LiterAlura.API.UI.Console_cmd;
import com.monsa.alura_challenge.LiterAlura.API.UI.Mensaje;
import com.monsa.alura_challenge.LiterAlura.API.UI.Menu;

import jakarta.transaction.Transactional;

@Service
public class Services_opc_2 {

    private final Libro_repository libro_repository;

    public Services_opc_2(Libro_repository libro_repository) { 
        this.libro_repository = libro_repository;
    }

    /**
     * Consulta Base de datos y obtiene información
     * 
     * Este método consulta la Base de Datos del proyecto y obtiene información de todos
     * los libros registrados hasta el momento de la consulta.
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public void ListarLibrosRegistrados(Scanner scan) throws IOException, InterruptedException {

        Console_cmd.Clear_cmd();
        Menu.Menu_opc_2();
        
        List<JPA_libros> librosEnDB = new ArrayList<>();
        librosEnDB = libro_repository.findAllByOrderByIdlibroAsc();

        printLibroAll(librosEnDB);
        
        Menu.Menu_opc_2_cierre();
        Mensaje.ColorAmarillo("Presione 'enter' para regresar el menú anterior...");
        scan.nextLine();
        Console_cmd.Clear_cmd();
        
    }

    /**
     * Consulta Base de datos y obtiene información
     * 
     * Este método consulta la Base de Datos del proyecto y obtiene información de todos
     * los libros registrados hasta el momento de la consulta.
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    private void printLibroAll(List<JPA_libros> librosEnDB) {

        final String ANSI_Reset = "\u001B[0m";
        final String ANSI_Verde = "\u001B[32m";  
        final String ANSI_Morado = "\u001B[35m";

        System.out.printf("\nTotal de resultados: %s\n", librosEnDB.size());

        Menu.Menu_inter_libro();

        String color = ANSI_Verde;

        for (int i = 0; i < librosEnDB.size(); i++) {

            var libro = librosEnDB.get(i);
            System.out.printf(color + "\n>>> id Base de Datos: %s <<<" + ANSI_Reset + "\nTítulo: %s\n", libro.getIdlibro(), libro.getTitulo());
            String autoresNombres = libro.getAutores().stream()
                                                   .map(JPA_autores::getNombre)
                                                   .collect(Collectors.joining(" - "));
            System.out.printf("Autor(es): %s \nTotal de descargas: %s \n", autoresNombres, libro.getDescargas());
            System.out.printf("URL de lectura: %s \n", libro.getUrlString());

            List<String> idiomasNombres = libro.getIdiomas().stream()
                                                            .map(JPA_idiomas::getIdioma)
                                                            .collect(Collectors.toList());
           
            String resultado_idioma = idiomasNombres.stream().map(Codigo_idioma::fromCodigo)
                                                             .collect(Collectors.joining(" - "));

             System.out.printf("Idioma(s): %s \n", resultado_idioma);
            System.out.printf(color + ">>> id Gutendex: %s <<<\n" + ANSI_Reset, libro.getIdgutendex());

            if (i%2 == 0) {
                color = ANSI_Morado;
            } else {
                color = ANSI_Verde;
            }
          
        }

    }

}
