package org.example;

import java.nio.file.Path;
import java.sql.*;


public class Main {

    public static void main(String[] args) {
        // URL de la BBDD
        Path ruta = Path.of("src", "main", "resources", "f12006sqlite.db");

        Piloto pilotoParam = new Piloto( "JEJ", "Patricia", "Patri", "16-12-1994", "Spanish", "URL");

        OperacionesCRUDPilotos.crearPiloto(ruta, pilotoParam);


    }
}
