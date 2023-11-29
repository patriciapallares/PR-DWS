package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        Book libro1 = new Book("1", "Titulo 1", "Autor 1", 111, "2001");
        Book libro2 = new Book("2", "Titulo 2", "Autor 2", 222, "2002");
        Book libro3 = new Book("3", "Titulo 3", "Autor 3", 333, "2003");
        Book libro4 = new Book("4", "Titulo 4", "Autor 4", 444, "2004");
        Book libro5 = new Book("5", "Titulo 5", "Autor 5", 555, "2005");

        // 2. Crea una lista de libros (puedes utilizar una lista de objetos Book) y agrégale algunos libros de
        // ejemplo.
        List<Book> libros = new ArrayList<>();

        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);

        String rutaLibrosJson = "/home/daw2/Escriptori/PR-DWS/EJ4_JSON_BOOK/src/main/resources/libros.json";

        // 3. Utiliza la librería Jackson para serializar la lista de libros a un archivo JSON.
        escribirListaObjetosJson(libros,rutaLibrosJson);

        List<Book> nuevosLibros = new ArrayList<>();

        // 4. Luego, deserializa el archivo JSON de nuevo en una lista de libros.
        nuevosLibros = leerArrayObjetosJson(rutaLibrosJson);
    }


    public static void escribirListaObjetosJson(List<Book> libros, String rutaSring) {
        Path ruta = Path.of(rutaSring);
        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), libros);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    public static List<Book> leerArrayObjetosJson(String rutaSring) {
        Path ruta = Path.of(rutaSring);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 5. Implementa una función que permita buscar libros por título o autor y mostrar los resultados.


}