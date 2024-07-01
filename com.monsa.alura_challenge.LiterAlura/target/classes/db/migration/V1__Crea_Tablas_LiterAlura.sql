CREATE TABLE idiomas (
    id_idioma SERIAL PRIMARY KEY,
    idioma VARCHAR(255) NOT NULL
);

CREATE TABLE autores (
    id_autor SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_Nacimiento DATE,
    fecha_Fallecimiento DATE
);

CREATE TABLE libros (
    id_libro SERIAL PRIMARY KEY,
    id_gutendex VARCHAR(255) NOT NULL UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    url_libro VARCHAR(255) NOT NULL,
    descargas VARCHAR(255) NOT NULL,
    id_idioma INT,
    FOREIGN KEY (id_idioma) REFERENCES idiomas(id_idioma)
);

CREATE TABLE libro_autor (
    libro_id INT,
    autor_id INT,
    PRIMARY KEY (libro_id, autor_id),
    FOREIGN KEY (libro_id) REFERENCES libros(id_libro),
    FOREIGN KEY (autor_id) REFERENCES autores(id_autor)
);