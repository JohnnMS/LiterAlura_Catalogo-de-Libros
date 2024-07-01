package com.monsa.alura_challenge.LiterAlura.API.Service;

public interface I_ConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
