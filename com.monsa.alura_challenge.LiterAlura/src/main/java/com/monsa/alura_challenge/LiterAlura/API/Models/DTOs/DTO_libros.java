package com.monsa.alura_challenge.LiterAlura.API.Models.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DTO_libros(

    @JsonAlias("id") String id,
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<DTO_libros_persona> autores,
    @JsonAlias("languages") List<String> lenguaje,
    @JsonAlias("formats") DTO_libros_formato formato,
    @JsonAlias("download_count") String conteo_descargas,
    @JsonAlias("detail") String error

) {}
