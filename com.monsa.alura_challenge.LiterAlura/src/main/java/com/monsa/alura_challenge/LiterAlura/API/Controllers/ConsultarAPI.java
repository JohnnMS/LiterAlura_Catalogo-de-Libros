package com.monsa.alura_challenge.LiterAlura.API.Controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarAPI {

    public static String obtenerDatos(String titulo) throws UnsupportedEncodingException {

        String titulo_code = URLEncoder.encode(titulo, "UTF-8");
        String url;

        if (titulo_code.matches("\\d+")) {
            url = "https://gutendex.com/books/?ids=" + titulo_code; 
        } else {
            url = "https://gutendex.com/books/?search=" + titulo_code;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());       
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;

    }

}
