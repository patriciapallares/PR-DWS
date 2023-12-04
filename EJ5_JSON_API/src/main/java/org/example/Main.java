package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    static List<Receta> listaRecetas;
    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void main(String[] args) throws IOException {
        String cadena = "https://www.themealdb.com/api/json/v1/1/search.php?f=a";

        // El método objectMapper .readTree nos devuelve un objeto de tipo JsonNode.
        JsonNode root = objectMapper.readTree(new URL(cadena));

        // indicamos la rama en la que deseamos buscar
        // el método .traverse() visita cada nodo hijo y realiza operaciones personalizadas en cada uno
        listaRecetas = objectMapper.readValue(root.get("meals").traverse(), new TypeReference<>() {});

        listaRecetas.forEach(System.out::println);
        Path rutaRecetas = Path.of(".", "src", "main", "resources", "recetas.json");
        escribeJson(rutaRecetas);
    }



    }
   public static void escribeJson(Path ruta) {

        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), listaRecetas);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
        }
    }
