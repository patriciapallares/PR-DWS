package com.example.servicio;

import com.example.model.Manufacturer;

import java.util.List;
import java.util.Optional;


/*
CRUD
Create
Retrieve/Read
Update
Delete
*/
public interface ManufacturerService {

    // RETRIEVE
    // devuelve una lista de todos los Manufacturers
    List<Manufacturer> findAll();

    List<Manufacturer> findAllByYear(Integer year);

    // otros métodos posibles: calcular beneficios

    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByName(String name);

    // CREATE / UPDATE (tanto para crear como actualizar)
    Manufacturer save(Manufacturer manufacturer);

    // DELETE
    void deleteById(Long id);
    void deleteAll();

    // MÁS LÓGICA DE NEGOCIO

    // 1. Cálculo de coches fabricado
    // 2. Beneficios obtenidos
    // 3. ...
}
