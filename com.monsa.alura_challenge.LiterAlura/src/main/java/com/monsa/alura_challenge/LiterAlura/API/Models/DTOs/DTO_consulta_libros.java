package com.monsa.alura_challenge.LiterAlura.API.Models.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DTO_consulta_libros(

    @JsonAlias("count") String total_items,
    @JsonAlias("results") List<DTO_libros> libros

){}
