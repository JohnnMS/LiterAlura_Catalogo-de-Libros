package com.monsa.alura_challenge.LiterAlura.API.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DTO_libros_formato(

    @JsonAlias("text/html") String urlString,
    @JsonAlias("application/pdf") String pdfString

){}