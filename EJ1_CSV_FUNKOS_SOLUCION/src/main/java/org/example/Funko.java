package org.example;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class Funko implements Serializable {
    private String id;
    private String nombre;
    private String modelo;
    private double precio;
    //private String fechaLanzamiento;
    private LocalDate fechaLanzamiento;

    public Funko(String id, String nombre, String modelo, String precioString, String fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.precio = precioStringToDouble(precioString);
        this.fechaLanzamiento = LocalDate.parse(fechaLanzamiento);
    }

    public Funko() {
    }

    //para la opción 3
    public Funko(List<String> lista) {
        this.id = lista.get(0);
        this.nombre = lista.get(1);
        this.modelo = lista.get(2);
        this.precio = Double.parseDouble(lista.get(3));
        this.fechaLanzamiento = LocalDate.parse(lista.get(4));
        // this.fechaLanzamiento = LocalDate.parse(lista.get(4), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private double precioStringToDouble(String precioString) {
        try{
            return Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "main.Funko{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", fechaLanzamiento=" + fechaLanzamiento +
                '}';
    }
}
