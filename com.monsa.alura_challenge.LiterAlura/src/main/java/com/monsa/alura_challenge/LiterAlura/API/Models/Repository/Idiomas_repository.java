package com.monsa.alura_challenge.LiterAlura.API.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_idiomas;

public interface Idiomas_repository extends JpaRepository<JPA_idiomas, Long>{
    
    JPA_idiomas findIdByIdioma(String codigo_idioma);

}
