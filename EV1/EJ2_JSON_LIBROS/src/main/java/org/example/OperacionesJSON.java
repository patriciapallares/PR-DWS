package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OperacionesJSON {

    public static Libro leerObjetoJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), Libro.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Libro> leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // return objectMapper.readValue(ruta.toFile(), new TypeReference<List<Libro>>() { });
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirObjetoJson(Libro lenguaje) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirListaObjetosJson(List<Libro> lenguajes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguajes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosJson(List<Libro> lenguajes, Path ruta) {

        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), lenguajes);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        Path ficheroObjetoJson = Path.of(".", "src", "main", "resources", "lenguaje.json");
        System.out.println("\n**** Lectura de objeto JSON desde fichero ****");
        System.out.println(leerObjetoJson(ficheroObjetoJson));

        Path ficheroArrayObjetosJson = Path.of(".", "src", "main", "resources", "lenguajes.json");
        System.out.println("\n**** Lectura de array de objetos JSON desde fichero ****");
        leerArrayObjetosJson(ficheroArrayObjetosJson).forEach(System.out::println);

        Libro lenguaje1 = new Libro("Solidity", 180, false, List.of("María", "Sonia", "José Miguel"));
        Libro Libro = new Libro("Vyper", 160, false, List.of("Pepe", "Julián"));

        List<Libro> lenguajes = List.of(lenguaje1, Libro);

        System.out.println("\n**** Escritura de objeto JSON por consola ****");
        System.out.println(escribirObjetoJson(lenguaje1));

        System.out.println("\n**** Escritura de lista de objetos JSON por consola ****");
        System.out.println(escribirListaObjetosJson(lenguajes));

        Path ejemploEscrituraJson = Path.of(".", "src", "main", "resources", "escritura.json");

        System.out.println("\n**** Escritura de lista de objetos JSON a fichero ****");
        escribirListaObjetosJson(lenguajes, ejemploEscrituraJson);
        */

    }

}
