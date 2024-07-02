# LiterAlura_Catálogo-de-Libros
Versión final -1.0- del tercer challenge desarrollado en el proceso de formación con Alura LATAM & Oracle Next Education.

## DESCRIPCIÓN.
El reto "LiterAlura" es una aplicación diseñada para consultar y almacenar en una base de datos local información acerca 
de títulos literarios. La aplicación se desarrolla como parte del proceso de formación en Alura & Oracle Next Education. 
Con su desarrollo se busca aplicar los conocimientos adquiridos en el ámbito de la programación y formación como Backend.

"LiterAlura", se desarrolló en el lenguaje de programación Java 17 como base, usando distintas dependencias como Maven, Spring Boot,
Spring Data y Postgres. Adicionalmente, la aplicación realiza consultas y hace uso de datos de una API llamada "Gutendex", 
la cual fue lanzada alrededor del año 2018. Esta API nace como iniciativa del "Proyecto Gutenberg", siendo una biblioteca digital
que ofrece más de 60 mil libros electrónicos gratuitos, como los clásicos de la literatura que están en el dominio público.

Para mayor información visite: https://gutendex.com/

### Requisitos establecidos para el desarrollo del proyecto.
- La aplicación debe contar con una interfaz de usuario.
- La aplicación debe hacer uso de una API.
- Debe existir un análisis de la respuesta JSON de la API para manipular y mostrar apropiadamente los datos al usuario.
- Debe almacenar información solicitada por el usuario en una base de datos local.
 
## ¿CÓMO EMPEZAR?

### Componentes.
Toda la interacción con el usuario se realiza a través de una interfaz textual mediante la consola. La estructura incluye
un menú con cinco opciones iniciales dentro de un bucle de repetición, lo que permite al usuario seleccionar opciones 
numéricas y proporcionar datos para la aplicación. Cada opción en el menú principal lleva a un módulo distinto, donde se 
ejecuta cada componente del código para suministrar la información al usuario. La aplicación ofrece control de errores, 
control del tipo de dato ingresado e interfaz de usuario detallada con algunos colores distintivos.

### Modo de uso y diseños.

- Menú principal:

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/7cc2ced4-4972-4918-ac5f-17a2aaae1867)

- Opción 1: La aplicación permite buscar en la API el título literario deseado, ya sea por palabras clave o por el identificador Gutendex.
Una vez obtenidos los resultados, la aplicación permite realizar una nueva búsqueda, guardar el libro encontrado ingresando su
identificador Gutendex y, por supuesto, regresar al menú principal si lo desea.

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/af343b98-1919-449a-948f-86b470d198cc)

- Opción 2: La aplicación permite desplegar un listado completo de los libros almacenados en la base de datos. Dentro de esa información
se incluye una URL para la lectura del título, así como información adicional acerca del libro.

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/dfa5d060-f87d-47b8-a82f-7422fdf0243d)

- Opción 3: La aplicación permite desplegar un listado completo de los autores almacenados en la base de datos. Dentro de esa información
se incluye, si está disponible, la fecha de nacimiento y, si aplica, la fecha de fallecimiento.

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/cdd40494-7cce-4f8d-bad8-8c4430c218a3)

- Opción 4: La aplicación permite buscar uno o varios autores por una fecha específica, ya sea su fecha de nacimiento o de fallecimiento.

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/af342ed5-5d6b-471f-b6b0-1e3069666640)

- Opción 5: La aplicación permite listar los libros guardados en la base de datos, relacionándolos por un idioma específico.

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/a86b1508-e321-4f96-b1e8-cd7bbd91c878)

Control de excepciones: 

![image](https://github.com/JohnnMS/LiterAlura_Catalogo-de-Libros/assets/99614055/9bc094cf-ad8b-400a-a3a2-06cb45e98a87)


### Acceso al proyecto
- La aplicación principal se aloja e la ruta ...\src\main\java\com\monsa\alura_challenge\LiterAlura\API\ApiApplication.java
- Se debe crear una base de datos en Postgres con el nombre, usuario y clave de su preferencia. Para el uso de la aplicación,
se deben crear cuatro variables de entorno en su sistema operativo preferido con los siguientes nombres (sin comillas):
"DB_HOST", "DB_NAME_LA", "DB_USER", "DB_PSW", junto con la información correspondiente.

### Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Licencias de Dependencias

Este proyecto utiliza las siguientes bibliotecas y frameworks, cada uno con sus respectivas licencias:

- **Spring Boot**: Licenciado bajo la [Licencia Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).
- **Spring Data**: Licenciado bajo la [Licencia Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).
- **PostgreSQL**: Licenciado bajo la [Licencia PostgreSQL](https://www.postgresql.org/about/licence/).

### Demo

https://youtu.be/BMAsHdx2myw

### Autor
Johnn Montañez Sarmiento
