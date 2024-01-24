package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OperacionesJSON {

    public static Cliente leerObjetoJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), Cliente.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Cliente> leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
