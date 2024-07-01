package com.monsa.alura_challenge.LiterAlura.API.Models.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_libros;

public interface Libro_repository extends JpaRepository<JPA_libros, Long> {

    JPA_libros findIdByIdgutendex(String idgutendex);

    List<JPA_libros> findAllByOrderByIdlibroAsc();

    @Query("SELECT l FROM JPA_libros l JOIN l.idiomas i WHERE i.idioma = :idioma ORDER BY l.idlibro ASC")
    List<JPA_libros> findByIdioma(@Param("idioma") String idioma);

}
