package com.monsa.alura_challenge.LiterAlura.API.Models.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class JPA_autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private Long idautor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_Nacimiento", nullable = false)
    private String fechaNacimiento;
    
    @Column(name = "fecha_Fallecimiento", nullable = false)
    private String fechaFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<JPA_libros> libros;

}
