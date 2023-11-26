package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ColeccionAtletas {
    private List<AtletaFemenina> listaAtletas;

    // Constructor generado por IntelliJ
    public ColeccionAtletas(List<AtletaFemenina> listaAtletas) {
        this.listaAtletas = listaAtletas;
    }

    // Otro constructor obtenido de Test Unit / BookCollection
    public ColeccionAtletas(AtletaFemenina[] atletas) {
        this.listaAtletas = Arrays.asList(atletas);
    }
}
