CREATE TABLE libro_idioma (
    libro_id INT,
    idioma_id INT,
    PRIMARY KEY (libro_id, idioma_id),
    FOREIGN KEY (libro_id) REFERENCES libros(id_libro) ON DELETE CASCADE,
    FOREIGN KEY (idioma_id) REFERENCES idiomas(id_idioma) ON DELETE CASCADE
);