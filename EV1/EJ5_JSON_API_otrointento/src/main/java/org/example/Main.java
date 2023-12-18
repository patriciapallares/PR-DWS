package org.example;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class Main {
    static List<Receta> recetasList;
    //añadimos el new JavaTimeModule, ya que es un módulo de Jackson que nos permite serializar y deserializar tipos de datos de fechas como por ejemplo: LocalDate, LocalTime, ZoneDateTime, etc
    //para usralo, hay que meter una dependencia Maven "com.fasterxml.jackson.datatype"
    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void main(String[] args) throws IOException {
        //el método .readTree nos devuelve un objeto de tipo JsonNode.
        //recetas con la "a"
        JsonNode root = objectMapper.readTree(new URL("https://www.themealdb.com/api/json/v1/1/search.php?f=b"));

        //debemos indicar la rama en la que deseamos buscar
        //el método .traverse() visita cada nodo hijo y realiza operaciones personalizadas en cada uno
        recetasList = objectMapper.readValue
                (root.get("meals").traverse(), new TypeReference<>(){});

        recetasList.forEach(System.out::println );

        Path ficheroJSON = Path.of(".", "src", "main", "resources", "ejemploRecetas.json");

        escribirJSON(ficheroJSON);
    }

    static void escribirJSON(Path ficheroJSON) {
        try{
            // la siguiente línea formatea la salida JSON con sangrías y saltos de línea,
            // lo que hace que el JSON sea más legible para los humanos.
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ficheroJSON.toFile(), recetasList);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
