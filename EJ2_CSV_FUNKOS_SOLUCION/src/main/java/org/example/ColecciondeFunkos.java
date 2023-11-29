package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class ColecciondeFunkos implements Serializable {
    final String COMMA_DELIMITER = ",";
    List<Funko> listaCSV = new ArrayList<>();
    List<Funko> listaCSV2 = new ArrayList<Funko>();
    List<Funko> listaCSV3 = new ArrayList<Funko>();


    public ColecciondeFunkos(Path path) throws IOException {
        //Leer el fichero de funkos.csv y crear una lista de objetos main.Funko
        try (Stream<String> contenidoFichero = lines(path)) {
            listaCSV = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER)))
                    .skip(1)
                    .map(l -> new Funko(l.get(0), l.get(1), l.get(2), l.get(3), l.get(4))).toList();

            //opción 2
            /*
            contenidoFichero
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                    .forEach(fields -> {
                        Funko funko = new Funko(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4));
                        listaCSV.add(funko);
                    });

             */

            //opción 3
            Stream<String> contenidoFichero3 = Files.lines(path);
            listaCSV3 = contenidoFichero3.skip(1)
                        .map(l -> Arrays.asList(l.split(COMMA_DELIMITER)))
                        .map(Funko::new)
                        .toList();

            //opción4
            List<String> linea = Files.readAllLines(Path.of(".", "src", "main", "resources", "funkos.csv"));
            String[] fun;
            System.out.println("Todos los funkos");
            for (int i = 1; i < linea.size(); i++) {
                Funko funko = new Funko();
                fun = linea.get(i).split(",");
                funko.setId(fun[0]);
                funko.setNombre(fun[1]);
                funko.setModelo(fun[2]);
                funko.setPrecio(Double.parseDouble(fun[3]));
                funko.setFechaLanzamiento(LocalDate.parse(fun[4]));
                listaCSV2.add(funko);
                System.out.println(funko.toString());
            }
            System.out.println("Mostramos la opcion 3:");
            System.out.println(listaCSV2);

            //Ordenar la lista por precio. Para el primer ejercicio
            listaCSV = listaCSV.stream().sorted(Comparator.comparing(Funko::getPrecio)).toList();
        } catch (
                IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public ColecciondeFunkos(List<Funko> listaCSV) {
        this.listaCSV = listaCSV;
    }

    //Imprimimos el funko más caro. Opción 1
    public String imprimirFunkoMasCaro() {
        //opción 1
        listaCSV = listaCSV.stream().sorted(Comparator.comparing(Funko::getPrecio)).toList();
        return "El funko más caro es: " + listaCSV.get(listaCSV.size() - 1).getNombre() + " y cuesta " + listaCSV.get(listaCSV.size() - 1).getPrecio() + "€";
        //opción 2
        //return "El funko más caro es: " + listaCSV.stream().max(Comparator.comparing(Funko::getPrecio)).map(Funko::getNombre) + " y cuesta " + listaCSV.stream().max(Comparator.comparing(Funko::getPrecio)).map(Funko::getPrecio) + "€";
    }



    //Imprimimos la media de precio de TODOS los funkos
    public double imprimirMediaPrecio() {
        //opción 1
        return listaCSV.stream().mapToDouble(Funko::getPrecio).average().getAsDouble();
        //opción 2
        //return listaCSV.stream().mapToDouble(Funko::getPrecio).average().orElse(0.0);
    }

    //Imprimimos los funkos agrupados por modelo.Opción 1
    //Ordeno por modelo, e imprimo
    public void imprimirFunkoPorModelo() {
        listaCSV.stream().sorted(Comparator.comparing(Funko::getModelo)).forEach(System.out::println);
    }
    //Imprimimos los funkos agrupados por modelo.Opción 2
    //agrupo por modelo y Funko
    public void imprimirFunkoPorModelo2() {
        listaCSV.stream()
                .collect(Collectors.groupingBy(Funko::getModelo)).forEach((k, v) -> System.out.println(k + " " + v));
    }

    //Imprimimos el numero de funkos que hay de cada modelo
    public void imprimirNumeroFunkosModelo() {
        Map<String, Long> modeloCountMap = listaCSV.stream()
                .collect(Collectors.groupingBy(Funko::getModelo, Collectors.counting()));

        // Imprime el resultado.
        modeloCountMap.forEach((modelo, count) -> System.out.println("Modelo: " + modelo + ", Cantidad: " + count));
    }

    //Imprimimos el numero de funkos que salen en 2023
    public long imprimirNumeroFunkos(int anyo) {
        return listaCSV.stream().filter(f -> f.getFechaLanzamiento().getYear()==anyo).count();
    };

    public boolean serializar(ColecciondeFunkos colecciondeFunkos) {
        boolean serializado = false;
        try(FileOutputStream fos = new FileOutputStream(Path.of(".", "src", "main", "resources", "funkos.dat").toString());
            //Serializar el objeto colecciondeFunkos
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(colecciondeFunkos);
            serializado = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serializado;
    }

    public static ColecciondeFunkos desserializar(){
        ColecciondeFunkos coleccionFunkos = null;
        try(FileInputStream fis = new FileInputStream(Path.of(".", "src", "main", "resources", "funkos.dat").toString());
            //Serializar el objeto colecciondeFunkos
            ObjectInputStream ois = new ObjectInputStream(fis)){
            coleccionFunkos = (ColecciondeFunkos) ois.readObject();
            //muestro los funkos para comprobar que se han deserializado bien
            coleccionFunkos.listaCSV.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return coleccionFunkos;
    }

}
