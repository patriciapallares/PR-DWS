package ejemploJakson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibroManager {
    private static final String BOOKS_JSON_FILE = "libros.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Libro> libros = new ArrayList<>();
    static boolean found = false;

    public static void main(String[] args) {
        loadBooksFromJson();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Buscar libros por título o autor");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    searchBooks(scanner);
                    break;
                case 3:
                    displayAllBooks();
                    break;
                case 4:
                    saveBooksToJson();
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void loadBooksFromJson() {
        try {
            File file = new File(BOOKS_JSON_FILE);
            if (file.exists()) {
                libros.addAll(objectMapper.readValue(file, new TypeReference<List<Libro>>() {}));
                System.out.println("Libros cargados desde " + BOOKS_JSON_FILE);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los libros desde " + BOOKS_JSON_FILE);
        }
    }

    private static void saveBooksToJson() {
        try {
            objectMapper.writeValue(new File(BOOKS_JSON_FILE), libros);
            System.out.println("Libros guardados en " + BOOKS_JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error al guardar los libros en " + BOOKS_JSON_FILE);
        }
    }

    private static void addBook(Scanner scanner) {
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

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            // Crear un nuevo objeto Book y agregarlo a la lista de libros
            Libro newBook = new Libro(titulo, autor, anyo, isbn);
            libros.add(newBook);

            System.out.println("Libro agregado con éxito.");

    }

    private static void searchBooks(Scanner scanner) {
        // Implementar la búsqueda de libros por título o autor

            System.out.println("Buscar libros por título o autor:");

            System.out.print("Ingresa el término de búsqueda: ");
            String searchTerm = scanner.nextLine().toLowerCase(); // Convertir la búsqueda a minúsculas para ser insensible a mayúsculas y minúsculas



            System.out.println("Resultados de la búsqueda:");
            //IMPERATIVA
            /*
            for (Libro libro : libros) {
                if (libro.getTitulo().toLowerCase().contains(searchTerm) || libro.getAutor().toLowerCase().contains(searchTerm)) {
                    System.out.println(libro.toString());
                    found = true;
                }
            }

             */
//FUNCIONAL
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

    private static void displayAllBooks() {
        // Mostrar todos los libros en la lista
            if (libros.isEmpty()) {
                System.out.println("No hay libros en la lista.");
            } else {
                System.out.println("Lista de todos los libros:");
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            }


    }

}
