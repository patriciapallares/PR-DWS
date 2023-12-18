package org.example;
import java.io.*;
public class ContactoOutput
{
    private FileOutputStream file;
    private ObjectOutputStream output;
    // Abrir el fichero
    public void abrir()
            throws IOException
    {

        file = new FileOutputStream( "clientes.txt" );
        output = new ObjectOutputStream(file);
    }
    // Cerrar el fichero
    public void cerrar()
            throws IOException
    {
        if (output!=null)
            output.close();
    }
    // Escribir en el fichero
    public void escribir (Contacto contacto)
            throws IOException
    {
        if (output!=null)
            output.writeObject(contacto);
    }
}