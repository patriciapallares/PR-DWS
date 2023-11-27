package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OperacionesJSON {

    public static DeportistaFemenina leerObjetoJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), DeportistaFemenina.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<DeportistaFemenina> leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // return objectMapper.readValue(ruta.toFile(), new TypeReference<List<Lenguaje2>>() { });
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirObjetoJson(DeportistaFemenina lenguaje) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirListaObjetosJson(List<DeportistaFemenina> lenguajes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(lenguajes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosJson(List<DeportistaFemenina> lenguajes, Path ruta) {

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

    /*
    public static void main(String[] args) throws IOException {



        System.out.println("\n**** Lectura de objeto JSON desde fichero ****");
        System.out.println(leerObjetoJson(ficheroObjetoJson));

        Path ficheroArrayObjetosJson = Path.of(".", "src", "main", "resources", "lenguajes.json");
        System.out.println("\n**** Lectura de array de objetos JSON desde fichero ****");
        leerArrayObjetosJson(ficheroArrayObjetosJson).forEach(System.out::println);

        System.out.println("\n**** Escritura de objeto JSON por consola ****");
        System.out.println(escribirObjetoJson(depor1));

        System.out.println("\n**** Escritura de lista de objetos JSON por consola ****");
        System.out.println(escribirListaObjetosJson(deportistas));

        Path ejemploEscrituraJson = Path.of(".", "src", "main", "resources", "escritura.json");


    }
    */

}
