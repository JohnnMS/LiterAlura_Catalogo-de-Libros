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
public class Services_opc_4 {

    private final Autores_repository autores_repository;

    public Services_opc_4(Autores_repository autores_repository) { 
        this.autores_repository = autores_repository;
    }

    /**
     * Consulta Base de datos y obtiene información
     * 
     * Este método consulta la Base de Datos del proyecto y obtiene información de todos
     * los autores registrado. El método busca por fecha de nacimiento o falecimieto 
     * 
     * @param scan instancia que captura datos de usuario por teclado.
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public void ListarAutoresRegistradosPorFecha(Scanner scan) throws IOException, InterruptedException {
        
        Console_cmd.Clear_cmd();
        Menu.Menu_opc_4();

        String opc_tipo_fecha, opc_fecha;
        List<JPA_autores> autores = new ArrayList<>();
        final String ANSI_Reset = "\u001B[0m";
        final String ANSI_Cian = "\u001B[36m";  
        final String ANSI_Morado = "\u001B[35m";
        String color = ANSI_Cian;
        
        do {
            Mensaje.ColorAmarillo("""
                \n¿Desea buscar el autor por fecha de nacimiento o fallecimiento?
                1- Fecha de nacimiento
                2- Fecha de fallecimiento\n""");
            opc_tipo_fecha = scan.nextLine().trim().toLowerCase();

            Mensaje.ColorMorado("Escriba el año por el que desea consultar:");
            opc_fecha = scan.nextLine().trim().toLowerCase();

            if (opc_fecha.matches("\\d{4}") && opc_tipo_fecha.matches("^(1|2)$")) {
                break;
            } else {
                Mensaje.ColorRojo("Introduce únicamente opción '1'/'2' y solo cuatro dígitos en campo fecha. ¡Inténtalo de nuevo!");
            }

        } while (true);

        Mensaje.ColorVerde("Buscando...");

        switch (opc_tipo_fecha) {
            case "1":
                autores = autores_repository.findByFechaNacimientoOrderByIdautor(opc_fecha);
                break;
            default:
                autores = autores_repository.findByFechaFallecimientoOrderByIdautor(opc_fecha);
                break;
        }

        System.out.printf("\nTotal de resultados: %s\n", autores.size());
           
        Menu.Menu_inter_autores();

        for (int i = 0; i < autores.size(); i++) {

            var autor = autores.get(i);

            System.out.printf(color + "\n>>> id Base de Datos: %s <<<" + ANSI_Reset + "\nAutor: %s\n", autor.getIdautor(), autor.getNombre());
            System.out.printf("Año de nacimiento: %s\n", (autor.getFechaNacimiento().toString().equalsIgnoreCase("Desconocida"))? "---" : autor.getFechaNacimiento());
            System.out.printf("Año de fallecimiento: %s\n", (autor.getFechaFallecimiento().toString().equalsIgnoreCase("Desconocida"))? "---" : autor.getFechaFallecimiento());  

            if (i%2 == 0) {
                color = ANSI_Morado;
            } else {
                color = ANSI_Cian;
            }
          
        }

        Menu.Menu_opc_4_cierre();
        Mensaje.ColorAmarillo("Presione 'enter' para regresar el menú anterior...");
        scan.nextLine();
        Console_cmd.Clear_cmd();

    }
    
}
