package org.example;

import java.io.*;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        // Lectura de ficheros CSV con Files.lines en java.nio

        try {
            // ruta del mac
            String miRutaFunkos = "/Users/patriciapallares/IdeaProjects/PR-DWS/funkos/src/main/resources/funkos.csv";

            List<Funko> funkosFunkos = deserializacionDesdeCsv(miRutaFunkos);

            masCaro(funkosFunkos);

            precioMedio(funkosFunkos);
/*
            agrupadoPorModelo(funkosFunkos, "MARVEL");
            agrupadoPorModelo(funkosFunkos, "DISNEY");
            agrupadoPorModelo(funkosFunkos, "ANIME");
            agrupadoPorModelo(funkosFunkos, "OTROS");

            cantidadPorModelo(funkosFunkos, "MARVEL");
            cantidadPorModelo(funkosFunkos, "DISNEY");
            cantidadPorModelo(funkosFunkos, "ANIME");
            cantidadPorModelo(funkosFunkos, "OTROS");

            funkosPorAnyo(funkosFunkos, 2023);
*/
            // ruta de clase
            String miRutaBackup = "/home/daw2/Escriptori/PR-DWS/funkos/src/main/resources/backup.dat";

            // MÉTODO BACKUP
            // backup(funkosFunkos,miRutaBackup);

            // MÉTODO RESTORE
            // List<Funko> funkosRestored = restore(miRutaBackup);

            // TODO TESTEAR LOS CASOS DE LOS MÉTODOS CREADOS USANDO JUNIT Y MOCKITO
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    public static List deserializacionDesdeCsv(String ruta) throws IOException {
        List<Funko> lista = new ArrayList<>();
        try (Stream<String> contenidoFichero = Files.lines(Paths.get(ruta))) {
            List<List<String>> funkosString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();

            for (int i = 1; i < funkosString.size(); i++) {
                List<String> unFunko = funkosString.get(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Funko ejemplo = new Funko();
                ejemplo.setCod(unFunko.get(0));
                ejemplo.setNombre(unFunko.get(1));
                ejemplo.setModelo(unFunko.get(2));
                ejemplo.setPrecio(Double.parseDouble(unFunko.get(3)));
                ejemplo.setFecha_lanzamiento(LocalDate.parse(unFunko.get(4), formatter));

                lista.add(ejemplo);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }


    public static void backup(List<Funko> lista, String ruta) throws IOException {
        // Borra el contenido del archivo
        Files.write(Paths.get(ruta), new byte[0]); // Escribe un array de bytes vacío para limpiar el archivo

        // escribir en binario
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            objectOutputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Objetos Funko escritos en el archivo backup.dat");

        // escribir en txt
        // hago el backup como si fuera csv para que el restore sea más sencillo
        /*
        for (int i = 0; i < lista.size(); i++) {
            try {
                Files.writeString(Paths.get(ruta), lista.get(i).toString(),  StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        */
    }

    public static List restore(String ruta) throws IOException {
        // Restaurar de .dat
        List<Funko> funkosDatFunkos = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ruta))) {
            try {
                funkosDatFunkos = (List<Funko>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Objetos Funko leídos del archivo backup.dat");
        return funkosDatFunkos;

        // Restaurar de .txt
        /*
        Stream<String> contenidoFichero = Files.lines(Paths.get(ruta));
        List<List<String>> funkosTxtString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();

        for (int i = 0; i < funkosTxtString.size(); i++) {
            List<String> unFunko = funkosTxtString.get(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Funko ejemplo = new Funko();
            ejemplo.setCod(unFunko.get(0));
            ejemplo.setNombre(unFunko.get(1));
            ejemplo.setModelo(unFunko.get(2));
            ejemplo.setPrecio(Double.parseDouble(unFunko.get(3)));
            ejemplo.setFecha_lanzamiento(LocalDate.parse(unFunko.get(4), formatter));

            funkosTxtFunkos.add(ejemplo);
        }
        return funkosTxtFunkos;
        */



        // Leer la lista de objetos Persona del archivo .dat

    }


    public static Double masCaro(List<Funko> lista) {
        // SE PODRÍA HACER MEJOR
        Double precioMax = lista.stream()
                .map(Funko::getPrecio)
                .max(Double::compareTo)
                .orElse(null);

        // Funko más caro: NOMBRE
        System.out.println("FUNKO MÁS CARO:");
        lista.stream()
                .filter(funko -> funko.getPrecio() == precioMax)
                .map(Funko::getNombre)
                .forEach(System.out::println);

        return precioMax;
    }

    public static Double precioMedio(List<Funko> lista) {
        // Para conseguir dos decimales y € al final
        Locale locale = new Locale("es", "ES"); // Definir la localidad para español
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale); // Obtener un formateador de moneda para la localidad española
        formatoMoneda.setMaximumFractionDigits(2); // Establecer el número de decimales a dos

        Double mediaPrecioFunkos = lista.stream()
                .mapToDouble(Funko::getPrecio)
                .average().getAsDouble();

        String montoFormateado = formatoMoneda.format(mediaPrecioFunkos);
        System.out.println("PRECIO MEDIO: " + montoFormateado);

        return mediaPrecioFunkos;
    }

    public static void agrupadoPorModelo(List<Funko> lista, String modelo) {
        System.out.println("FUNKOS " + modelo + ":");
        lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo))
                .map(Funko::getNombre)
                .forEach(System.out::println);
    }

    public static List agrupadoPorModeloTest(List<Funko> lista, String modelo) {
        List<Funko> listaRetornar = new ArrayList<>();
        System.out.println("FUNKOS " + modelo + ":");
        lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo))
                .map(Funko::getNombre)
                .forEach(System.out::println);
        return lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo)).toList();
    }


    public static void cantidadPorModelo(List<Funko> lista, String modelo) {
        System.out.println("NÚMERO DE FUNKOS " + modelo + ":");
        long cantidadMarvel = lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo))
                .count();

        System.out.println(cantidadMarvel);
    }

    public static void funkosPorAnyo(List<Funko> lista, int anyo) {
        System.out.println("FUNKOS DE " + anyo);
        lista.stream()
                .filter(funko -> funko.getFecha_lanzamiento().getYear() == anyo)
                .map(Funko::getNombre)
                .forEach(System.out::println);


    }

}