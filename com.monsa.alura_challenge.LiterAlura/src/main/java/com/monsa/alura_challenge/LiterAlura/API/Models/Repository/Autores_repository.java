package com.monsa.alura_challenge.LiterAlura.API.Models.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monsa.alura_challenge.LiterAlura.API.Models.Entity.JPA_autores;

public interface Autores_repository extends JpaRepository<JPA_autores, Long>{

    JPA_autores findIdByNombreAndFechaNacimiento(String nombre, String fechaNacimiento);

    @Query("SELECT a FROM JPA_autores a WHERE a.nombre <> 'Desconocido' ORDER BY a.id ASC")
    List<JPA_autores> findAllAutoresExceptoDesconocidoOrderedById();

    List<JPA_autores> findByFechaNacimientoOrderByIdautor(String fechaNacimiento);
    List<JPA_autores> findByFechaFallecimientoOrderByIdautor(String fechaFallecimiento);

}
