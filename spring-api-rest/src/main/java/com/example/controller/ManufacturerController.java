package com.example.controller;


import com.example.model.Manufacturer;
import com.example.servicio.ManufacturerService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManufacturerController {


    // métodos a nivel de servicio

    private ManufacturerService service;

    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    /*
    GET http://localhost:8080/api/manufacturers
    * */
    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> findAll(){
        List<Manufacturer> manufacturers = this.service.findAll();
        if (manufacturers.isEmpty())
                return ResponseEntity.notFound().build();
        return ResponseEntity.ok(manufacturers);
    }

    /*
    GET http://localhost:8080/api/manufacturers/year/1990
    * */
    @GetMapping("/manufacturers/year/{year}")
    public ResponseEntity<List<Manufacturer>> findAllByYear(@PathVariable Integer year){
        List<Manufacturer> manufacturers = this.service.findAllByYear(year);
        if (manufacturers.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(manufacturers);
    }

    /*
    GET http://localhost:8080/api/manufacturers/7
    * */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    *
    * */

    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer){
        // comprobaciones (no funciona, paso)

      //  if(manufacturer.getId() != null)
      //      return ResponseEntity.badRequest().build();
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

    // Las urls no están repetidas porq cada una usa un método http diferente
    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

    @DeleteMapping("/manufacturers/{identifier}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable("identifier") Long id){
        this.service.deleteById(id);
        // Hab´ria que hacer una comprobación de si el manufacturer existe o no y devolver
        // not found o así
        return ResponseEntity.noContent().build();
    }
}

