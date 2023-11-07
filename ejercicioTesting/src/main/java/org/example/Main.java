package org.example;

public class Main {
    public static void main(String[] args) {

        // Crea una clase con un método “número_mayor”, que dados 3 números por
        //parámetros, te diga cuál de los 3 es el mayor

        //
    }

    public int numero_mayor(int num1, int num2, int num3) {
        int mayor = num1;

        if (num2 > mayor) {
            mayor = num2;
        }

        if (num3 > mayor) {
            mayor = num3;
        }

        return mayor;
    }
}


