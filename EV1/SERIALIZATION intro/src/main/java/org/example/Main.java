package org.example;

public class Main {
    public static void main(String[] args) {

        Contacto contacto1 = new Contacto("Claudia", "123", "email", "dirección", 1, 0.6);
        Contacto contacto2 = new Contacto("Fatine", "123", "email", "dirección", 1, 0.6);


     //    Escritura de datos
        ContactoOutput salida = new ContactoOutput();

        try {
            salida.abrir();
            salida.escribir( contacto1 );
            salida.escribir( contacto2 );
            salida.cerrar();
        } catch (Exception e){

        }

       //  Lectura de datos
        Contacto contacto;
        ContactoInput entrada = new ContactoInput();

        try {
            entrada.abrir();
            do {
                contacto = entrada.leer();
                System.out.println(contacto);

                System.out.println(contacto.getNombre());
            } while (contacto != null);
            entrada.cerrar();
        }catch (Exception e){

        }

    }
}