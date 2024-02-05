package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constructor {
    private int constructorid;
    private String constructorref;
    private String name;
    private String nationality;
    private String url;

    public Constructor(String constructorref, String name, String nationality, String url) {
        this.constructorref = constructorref;
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }
}
