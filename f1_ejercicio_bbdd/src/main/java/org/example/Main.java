package org.example;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Piloto pilotoP = new Piloto( "PAT", "Patricia", "Pallarés", "1995-12-16", "Spanish", "URL");

       // OperacionesCRUDNuevo.CrearPiloto(pilotoP);


        Piloto pilotoNuevo = OperacionesCRUDNuevo.LeerPiloto(38);

        System.out.println("nombre piloto nuevo: " + pilotoNuevo.getForename());
    }

}
