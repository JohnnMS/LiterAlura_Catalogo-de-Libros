package com.monsa.alura_challenge.LiterAlura.API.Principal;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.monsa.alura_challenge.LiterAlura.API.Service.Services_opc_1;
import com.monsa.alura_challenge.LiterAlura.API.Service.Services_opc_2;
import com.monsa.alura_challenge.LiterAlura.API.Service.Services_opc_3;
import com.monsa.alura_challenge.LiterAlura.API.Service.Services_opc_4;
import com.monsa.alura_challenge.LiterAlura.API.Service.Services_opc_5;
import com.monsa.alura_challenge.LiterAlura.API.UI.Console_cmd;
import com.monsa.alura_challenge.LiterAlura.API.UI.Mensaje;
import com.monsa.alura_challenge.LiterAlura.API.UI.Menu;

@Component
public class Principal {

    private Scanner scan = new Scanner(System.in);
    private Services_opc_1 services_opc_1;
    private Services_opc_2 services_opc_2;
    private Services_opc_3 services_opc_3;
    private Services_opc_4 services_opc_4;
    private Services_opc_5 services_opc_5;

    public Principal(Services_opc_1 services_opc_1, Services_opc_2 services_opc_2, Services_opc_3 services_opc_3, 
                     Services_opc_4 services_opc_4, Services_opc_5 services_opc_5) {
        this.services_opc_1 = services_opc_1;
        this.services_opc_2 = services_opc_2;
        this.services_opc_3 = services_opc_3;
        this.services_opc_4 = services_opc_4;
        this.services_opc_5 = services_opc_5;
    }

    private String response = null;

    public void run_menu() throws IOException, InterruptedException {

        Console_cmd.Clear_cmd();

        do {
            Menu.Menu_ppal();
            Mensaje.ColorAmarillo("\nEscoja una opción por número o escriba 'salir' para finalizar:");
            response = scan.nextLine().trim();

            if (response.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                
                switch (response) {
                    case "1":
                        services_opc_1.BuscarLibroPorNombre(scan);
                        break;
                    case "2":
                        services_opc_2.ListarLibrosRegistrados(scan);
                        break;
                    case "3":
                        services_opc_3.ListarAutoresRegistrados(scan);
                        break;
                    case "4":
                        services_opc_4.ListarAutoresRegistradosPorFecha(scan);
                        break;
                    case "5":
                        services_opc_5.ListarLibrosPorIdioma(scan);
                        break;
                    default:
                        Mensaje.ColorRojo("¡Seleccione una opción válida!\nPresione 'Enter' e inténte de nuevo...");
                        scan.nextLine().trim();
                        Console_cmd.Clear_cmd();
                        break;
                }   
           
            } catch (ConnectException e) {
                Mensaje.ColorRojo("Error de conexión a internet: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (UnknownHostException e) {
                Mensaje.ColorRojo("Host desconocido: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (SocketException e) {
                Mensaje.ColorRojo("Error de socket: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (IOException e) {
                Mensaje.ColorRojo("Error de entrada/salida: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (InterruptedException e) {
                Mensaje.ColorRojo("Operación interrumpida: " + e.getMessage());
                Thread.currentThread().interrupt();
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (DataAccessException e) {
                Mensaje.ColorRojo("Error de acceso a datos JPA: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            } catch (Exception e) {
                Mensaje.ColorRojo("Ocurrió un error inesperado: " + e.getMessage());
                scan.nextLine().trim();
                Console_cmd.Clear_cmd();
            }

        } while (true);        
        scan.close();

    }

}
