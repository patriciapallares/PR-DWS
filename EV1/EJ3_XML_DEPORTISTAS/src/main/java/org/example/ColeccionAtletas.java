package org.example;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@JacksonXmlRootElement(localName = "atletas")
public class ColeccionAtletas {
    List<AtletaFemenina> listaAtletas;

    // Constructor generado por IntelliJ
    public ColeccionAtletas(List<AtletaFemenina> listaAtletas) {
        this.listaAtletas = listaAtletas;
    }

    // Otro constructor obtenido de Test Unit / BookCollection
/*    public ColeccionAtletas(AtletaFemenina[] atletas) {
        this.listaAtletas = Arrays.asList(atletas);
    }*/

    public void anyadirAtleta(AtletaFemenina atleta){
        this.listaAtletas.add(atleta);
        System.out.println("Se ha añadido una atleta");
    }

    public List<AtletaFemenina> getListaAtletas() {
        return listaAtletas;
    }


}
