package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private final static String COMMA_DELIMITER = ",";


    public static void main(String[] args) {
        // Lectura de ficheros CSV con Files.lines en java.nio
        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "funkos.csv"))) {
            List<List<String>> funkosString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();

            // funkosString.forEach(System.out::println);

             List<Funko> funkosFunkos = new ArrayList<>();

            // funkosString.remove(0);

            for (int i = 1; i < funkosString.size(); i++) {
                List<String> unFunko = funkosString.get(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Funko ejemplo = new Funko();
                ejemplo.setCod(unFunko.get(0));
                ejemplo.setNombre(unFunko.get(1));
                ejemplo.setModelo(unFunko.get(2));
                ejemplo.setPrecio(Double.parseDouble(unFunko.get(3)));
                ejemplo.setFecha_lanzamiento(LocalDate.parse(unFunko.get(4), formatter));

                funkosFunkos.add(ejemplo);
            }

           // funkosFunkos.forEach(System.out::println);

            // Funko más caro
            double precioMax = funkosFunkos.stream()
                    .map(Funko::getPrecio)
                    .max(Double::compareTo)
                    .orElse(null);

           funkosFunkos.stream()
                    .filter(funko -> funko.getPrecio() == precioMax)
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println(precioMax);

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}