package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.example.OperacionesJSON.*;

public class Main {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        DeportistaFemenina depor1 = new DeportistaFemenina("Fatine", "Comba", 19, "China");
        DeportistaFemenina depor2 = new DeportistaFemenina("Claudia", "Samba", 19, "Japón");
        DeportistaFemenina depor3 = new DeportistaFemenina("Sara", "Karts", 19, "Filipinas");
        DeportistaFemenina depor4 = new DeportistaFemenina("Laura", "Skate", 19, "Korea");

        Path rutaDeporJson = Path.of(".", "src", "main", "resources", "deportistas_femeninas.json");

        List<DeportistaFemenina> deportistas = List.of(depor1, depor2, depor3, depor4);

        System.out.println("\n**** Escritura de lista de objetos JSON a fichero ****");
        escribirListaObjetosJson(deportistas, rutaDeporJson);

        System.out.println("\n**** Lectura de array de objetos JSON desde fichero ****");
        leerArrayObjetosJson(rutaDeporJson).forEach(System.out::println);
    }
}