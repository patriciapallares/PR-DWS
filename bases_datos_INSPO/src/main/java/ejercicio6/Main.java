package ejercicio6;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Piloto insert = new Piloto("PEP", "Pepe", "Jorques", Date.valueOf("2003-01-15"), "Spanish");
        Piloto actualizar = new Piloto("LOC", "Sara", "Martinez", Date.valueOf("1999-05-06"), "Spanish");
        OperacionesCRUDPilotos operaciones = new OperacionesCRUDPilotos();

        operaciones.crearPiloto(insert);
        System.out.println("------LEER PILOTO POR ID------");
        operaciones.leerPiloto(2);
        System.out.println("------LEER PILOTOS------");
        operaciones.leerPilotos();
        System.out.println("------ACTUALIZAR PILOTOS------");
        operaciones.actualizarPiloto(2, actualizar);
        operaciones.leerPilotos();
        System.out.println("------BORRAR PILOTO------");
        operaciones.borrarPiloto(38);
        operaciones.leerPilotos();
        System.out.println("------CLASIFICACIÓN POR PUNTOS (piloto)------");
        operaciones.mostrarClasificacionPiloto();
        System.out.println("------CLASIFICACIÓN POR PUNTOS (escuderia)------");
        operaciones.mostrarClasificacionConstructores();
    }
}
