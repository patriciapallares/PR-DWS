package org.example;

import java.io.Serializable;
import java.util.Date;
public class Contacto implements Serializable
{

    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private Date nacimiento;
    private int grupo;
    private double deuda;

    public Contacto(String nombre, String telefono, String email, String direccion, int grupo, double deuda) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.grupo = grupo;
        this.deuda = deuda;
    }


    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public int getGrupo() {
        return grupo;
    }

    public double getDeuda() {
        return deuda;
    }
}