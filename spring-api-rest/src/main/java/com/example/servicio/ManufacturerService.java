package com.example.servicio;

import com.example.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    // devuelve una lista de todos los Manufacturers
    List<Manufacturer> findAll();

    List<Manufacturer> findAllByYear(Integer year);

    // otros métodos posibles: calcular beneficios


    Optional<Manufacturer> findById(Long id);
}
