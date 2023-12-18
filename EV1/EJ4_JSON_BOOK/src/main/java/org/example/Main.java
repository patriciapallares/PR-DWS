package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<Book> libros = new ArrayList<>();
    private static boolean found = false;
    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);

        Book libro1 = new Book("1", "Titulo 1", "Autor 1", 111, 2001);
        Book libro2 = new Book("2", "Titulo 2", "Autor 2", 222, 2001);
        Book libro3 = new Book("3", "Titulo 3", "Patri", 333, 2003);
        Book libro4 = new Book("4", "Titulo 4", "Autor 4", 444, 2004);
        Book libro5 = new Book("5", "Titulo 5", "Autor 5", 555, 2005);

        // 2. Crea una lista de libros (puedes utilizar una lista de objetos Book) y agrégale algunos libros de
        // ejemplo.

        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);

        // String rutaLibrosJson = "/home/daw2/Escriptori/PR-DWS/EJ4_JSON_BOOK/src/main/resources/libros.json";
        Path rutaLibros = Path.of(".", "src", "main", "resources", "books.json");

        // 3. Utiliza la librería Jackson para serializar la lista de libros a un archivo JSON.
        escribirListaObjetosJson(libros,rutaLibros);

        List<Book> nuevosLibros = new ArrayList<>();

        // 4. Luego, deserializa el archivo JSON de nuevo en una lista de libros.
        nuevosLibros = leerArrayObjetosJson(rutaLibros);

        int choice;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Buscar libros por título o autor");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            choice = reader.nextInt();
            reader.nextLine(); // Consumir la nueva línea

            switch (choice) {
                case 1:
                    anyadirLibro(reader);
                    break;
                case 2:
                    buscarLibroPorAutorOTitulo(reader);
                    break;
                case 3:
                    mostrarLibros();
                    break;
                case 4:
                    saveBooksToJson(rutaLibros);
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (choice != 4);

        reader.close();
    }


    public static void escribirListaObjetosJson(List<Book> libros, Path ruta) {

        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), libros);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    private static void saveBooksToJson(Path ruta) {
        try {
            objectMapper.writeValue(ruta.toFile(), libros);
            System.out.println("Libros guardados ");
        } catch (IOException e) {
            System.out.println("Error al guardar los libros");
        }
    }

    public static List<Book> leerArrayObjetosJson(Path ruta) {
        // Path ruta = Path.of(rutaSring);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarLibroPorAutorOTitulo(Scanner scanner) {
        // 5. Implementa una función que permita buscar libros por título o autor y mostrar los resultados.
        System.out.println("Buscar libros por título o autor:");
        System.out.print("Ingresa el término de búsqueda: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        System.out.println("Resultados de la búsqueda:");

        libros.stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(searchTerm) || libro.getAutor().toLowerCase().contains(searchTerm))
                .forEach(libro -> {
                    System.out.println(libro.toString());
                    found = true;
                });

        if (!found) {
            System.out.println("No se encontraron libros que coincidan con el término de búsqueda.");
        }
    }

    private static void mostrarLibros() {
        // Mostrar todos los libros en la lista
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la lista.");
        } else {
            System.out.println("Lista de todos los libros:");
            for (Book libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }

    private static void anyadirLibro(Scanner scanner) {
        // Solicitar información del libro al usuario (título, autor, año, ISBN)
        // Crear un nuevo objeto Book y agregarlo a la lista de libros

        System.out.println("Agregar un nuevo libro:");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Año de publicación: ");
        int anyo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Número de páginas: ");
        int pag = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        // Crear un nuevo objeto Book y agregarlo a la lista de libros
        Book newBook = new Book(isbn, titulo, autor, pag, anyo);
        libros.add(newBook);

        System.out.println("Libro agregado con éxito.");

    }
}