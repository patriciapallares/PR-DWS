package org.example;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        ColeccionProductos cFunkos = new ColeccionProductos(Path.of(".", "src", "main", "resources", "Ej03-LeerFichero.csv"));

    }
}