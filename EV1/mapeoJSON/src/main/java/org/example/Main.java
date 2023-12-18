package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        try {
            Path ficheroPersonaJson = Path.of(".", "src", "main", "resources", "personas.json");

            //arrayList persona
            ArrayList<Persona> personas = JSON_MAPPER.readValue(ficheroPersonaJson.toFile(), new TypeReference<>() {});

            personas.forEach(System.out::println);


            Path ficheroProductoJson = Path.of(".", "src", "main", "resources", "productos.json");

            ////arrayList productos

            ArrayList<Persona> productos = JSON_MAPPER.readValue(ficheroProductoJson.toFile(), new TypeReference<>() {});
            productos.forEach(System.out::println);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}