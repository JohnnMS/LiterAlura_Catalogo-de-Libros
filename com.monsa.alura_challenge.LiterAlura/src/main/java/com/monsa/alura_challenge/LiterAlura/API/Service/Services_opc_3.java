package com.monsa.alura_challenge.LiterAlura.API.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_autores;
import com.monsa.alura_challenge.LiterAlura.API.Models.Repository.Autores_repository;
import com.monsa.alura_challenge.LiterAlura.API.UI.Console_cmd;
import com.monsa.alura_challenge.LiterAlura.API.UI.Mensaje;
import com.monsa.alura_challenge.LiterAlura.API.UI.Menu;

import jakarta.transaction.Transactional;

@Service
public class Services_opc_3 {

    private final Autores_repository autores_repository;

    public Services_opc_3(Autores_repository autores_repository) { 
        this.autores_repository = autores_repository;
    }

    /**
     * Consulta base de datos y obtiene información Autores
     * 
     * Este método consulta la Base de Datos del proyecto y obtiene información acerca 
     * de los autores registrados, tales como nombre, fecha de nacimiento y de fallecimiento si aplica.
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public void ListarAutoresRegistrados(Scanner scan) throws IOException, InterruptedException {
        
        Console_cmd.Clear_cmd();
        Menu.Menu_opc_3();

        List<JPA_autores> autoresEnDB = new ArrayList<>();
        autoresEnDB = autores_repository.findAllAutoresExceptoDesconocidoOrderedById();

        printAutorAll(autoresEnDB);

        Menu.Menu_opc_3_cierre();
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
    private void printAutorAll(List<JPA_autores> autoresEnDB) {

        final String ANSI_Reset = "\u001B[0m";
        final String ANSI_Cian = "\u001B[36m";  
        final String ANSI_Morado = "\u001B[35m";

        System.out.printf("\nTotal de resultados: %s\n", autoresEnDB.size());

        Menu.Menu_inter_autores();

        String color = ANSI_Cian;

        for (int i = 0; i < autoresEnDB.size(); i++) {

            var autor = autoresEnDB.get(i);

            System.out.printf(color + "\n>>> id Base de Datos: %s <<<" + ANSI_Reset + "\nAutor: %s\n", autor.getIdautor(), autor.getNombre());
            System.out.printf("Año de nacimiento: %s\n", (autor.getFechaNacimiento().toString().equalsIgnoreCase("Desconocida"))? "---" : autor.getFechaNacimiento());
            System.out.printf("Año de fallecimiento: %s\n", (autor.getFechaFallecimiento().toString().equalsIgnoreCase("Desconocida"))? "---" : autor.getFechaFallecimiento());  

            if (i%2 == 0) {
                color = ANSI_Morado;
            } else {
                color = ANSI_Cian;
            }
          
        }

    }
    
}
