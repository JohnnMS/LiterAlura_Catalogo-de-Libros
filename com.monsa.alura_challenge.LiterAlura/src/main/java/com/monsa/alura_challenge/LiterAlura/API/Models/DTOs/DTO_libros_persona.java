package com.monsa.alura_challenge.LiterAlura.API.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DTO_libros_persona(

    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") String fecha_nacimiento,
    @JsonAlias("death_year") String fecha_fallecimiento

){}
