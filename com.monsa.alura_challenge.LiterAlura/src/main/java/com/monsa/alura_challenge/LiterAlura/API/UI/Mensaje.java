package com.monsa.alura_challenge.LiterAlura.API.UI;

public class Mensaje {

    private static final String ANSI_Reset = "\u001B[0m";
    private static final String ANSI_Rojo = "\u001B[31m";    
    private static final String ANSI_Verde = "\u001B[32m";  
    private static final String ANSI_Amarillo = "\u001B[93m";
    private static final String ANSI_Morado = "\u001B[35m";

    public static void ColorRojo(String texto) {
        System.err.println(ANSI_Rojo + texto + ANSI_Reset);
    }

    public static void ColorVerde(String texto) {
        System.out.println(ANSI_Verde + texto + ANSI_Reset);
    }

    public static void ColorAmarillo(String texto) {
        System.out.println(ANSI_Amarillo + texto + ANSI_Reset);
    }

    public static void ColorMorado(String texto) {
        System.out.println(ANSI_Morado + texto + ANSI_Reset);
    }

} 
