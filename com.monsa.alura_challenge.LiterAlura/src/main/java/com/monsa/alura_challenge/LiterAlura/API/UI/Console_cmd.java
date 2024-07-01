package com.monsa.alura_challenge.LiterAlura.API.UI;

public class Console_cmd {

    /**
     * Descripción: función que permite borrar datos de consola para graficar nuevamente y hacer cambios de menú estático.
     * Opción para windows, linux y mac
     */
    public static void Clear_cmd() { 
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {   
            Mensaje.ColorRojo("Operación interrumpida clear_cmd: " + e.getMessage());
        }
    }

}
