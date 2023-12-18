package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pelicula {
    @JsonProperty("title")
    private String titulo;
    @JsonProperty("year")
    private int anyo;
}
