package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// sólo se queda con la info igual a los atributos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Receta {
    private String idMeal;
    // nombre
    private String strMeal;
    // categoria
    private String strCategory;
    // area?
    private String strArea;
}
