package org.example;
import java.io.*;
public class ContactoInput
{
    private FileInputStream file;
    private ObjectInputStream input;
    public void abrir()
            throws IOException
    {
        file = new FileInputStream( "clientes.txt" );
        input = new ObjectInputStream (file);
    }
    public void cerrar()
            throws IOException
    {
        if (input!=null )
            input.close();
    }
    public Contacto leer ()
            throws IOException, ClassNotFoundException
    {
        Contacto contacto = null;
        if (input!=null) {
            try {
                contacto = (Contacto) input.readObject();
            } catch (EOFException eof) {
// Fin del fichero
            }
        }
        return contacto;
    }
}
