package org.example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Actor {

    @JsonProperty("name")
    private String nombre;
    @JsonProperty("sex")
    private String sexo;
    @JsonProperty("yearOfBirth")
    private int anyoNacimiento;
    @JsonProperty("movies")
    private List<Pelicula> peliculas;



    public void muestraNombreYAnyo(){
        System.out.println("Nombre: " + nombre + " AñoNacimiento: " + anyoNacimiento);
    }
}
