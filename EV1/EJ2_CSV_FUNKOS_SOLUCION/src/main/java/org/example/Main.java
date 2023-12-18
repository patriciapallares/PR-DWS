package org.example;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        ColecciondeFunkos cFunkos = new ColecciondeFunkos(Path.of(".", "src", "main", "resources", "funkos.csv"));

        //Imprimimos el funko más caro
        System.out.println("El funko más caro es:" + cFunkos.imprimirFunkoMasCaro());

        //Imprimimos la media de precio de los funkos
        System.out.println("La media de precio de los funkos es: %s€%n" + cFunkos.imprimirMediaPrecio());

        //Imprimimos los funkos agrupados por modelo
        System.out.println("Imprimimos los funkos agrupados por modelo");
        cFunkos.imprimirFunkoPorModelo2();

        //Imprimimos el numero de funkos que hay de cada modelo
        System.out.println("Imprimimos el número de funkos que hay de cada modelo");
        cFunkos.imprimirNumeroFunkosModelo();

        //Imprimimos el numero de funkos que salen en 2023
        System.out.println("El numero de funkos que salen en 2023 es: " + cFunkos.imprimirNumeroFunkos(2023));

        //Serializar el objeto colecciondeFunkos
        cFunkos.serializar(cFunkos);

        //Deserializar el objeto colecciondeFunkos
        cFunkos.desserializar();

    }
}
