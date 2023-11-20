package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

            // Funko más caro: PRECIO
            double precioMax = funkosFunkos.stream()
                    .map(Funko::getPrecio)
                    .max(Double::compareTo)
                    .orElse(null);
            // Funko más caro: NOMBRE
            System.out.println("FUNKO MÁS CARO:");
           funkosFunkos.stream()
                    .filter(funko -> funko.getPrecio() == precioMax)
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("LA MEDIA ES:");
            // media de precios de funkos
            double mediaPrecioFunkos = funkosFunkos.stream()
                    .mapToDouble(Funko::getPrecio)
                    .average().getAsDouble();
            System.out.println(mediaPrecioFunkos);
/*

            // TODO -  CONVERTIR EN FUNCIÓN CON PARÁMETROS
            // funkos agrupados por modelos
            System.out.println("FUNKOS MARVEL:");
            funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "MARVEL"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("FUNKOS DISNEY:");
            funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "DISNEY"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("FUNKOS ANIME:");
            funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "ANIME"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("FUNKOS OTROS:");
            funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "OTROS"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            */

            // TODO -  CONVERTIR EN FUNCIÓN CON PARÁMETROS

            System.out.println("NÚMERO DE FUNKOS MARVEL:");
            long cantidadMarvel = funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "MARVEL"))
                    .count();

            System.out.println(cantidadMarvel);

            System.out.println("NÚMERO DE FUNKOS DISNEY:");
            long cantidaDisney = funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "DISNEY"))
                    .count();

            System.out.println(cantidaDisney);


            System.out.println("NÚMERO DE FUNKOS ANIME:");
            long cantidadAnime = funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "ANIME"))
                    .count();

            System.out.println(cantidadAnime);


            System.out.println("NÚMERO DE FUNKOS OTROS:");
            long cantidadOtros = funkosFunkos.stream()
                    .filter(funko -> Objects.equals(funko.getModelo(), "OTROS"))
                    .count();

            System.out.println(cantidadOtros);

            /*
            // date.getYear() >= yearStart && date.getYear() <= yearEnd

            LocalDate dateStart =  LocalDate.of(23, 1, 1);
            LocalDate dateEnd =  LocalDate.of(23, 12, 31);

            funkosFunkos.stream().filter(funko -> funko.getFecha_lanzamiento() >= dateStart && funko.getFecha_lanzamiento()<= dateStart);

            */

            // funkos del 2023
            System.out.println("fUNKO DEL 2023");
            funkosFunkos.stream()
                    .filter(funko -> funko.getFecha_lanzamiento().getYear() == 2023)
                    .map(Funko::getNombre)
                    .forEach(System.out::println);



        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}