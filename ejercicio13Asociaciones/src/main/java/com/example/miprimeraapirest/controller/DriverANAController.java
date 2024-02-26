package com.example.miprimeraapirest.controller;

import com.example.miprimeraapirest.model.Driver;
import com.example.miprimeraapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//para trabajar con APIs, RestController
//@RestController
//para trabajar con vistas y mostrar una tabla con los drivers, @Controller
@Controller
@RequestMapping("/api")
public class DriverRestController {
    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService service){
        this.driverService=service;
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAll(){
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/drivers/{code}")
    public ResponseEntity<Optional<Driver>> getByCode(@PathVariable String code){
        //Hay 2 maneras de hacerlo, funcional o imperativa
        //Funcional
        /*
        return this.driverService.getDriverByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

         */


        //Imperativo
        Optional<Driver> d = this.driverService.getDriverByCode(code);
        if(d.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    /*
    POST http://localhost:8080/api/drivers
     */
    @PostMapping("/drivers")
    public ResponseEntity<Driver> create(@RequestBody Driver driver) {
        if (driver.getDriverid() != null)
            return ResponseEntity.badRequest().build();

        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    @PutMapping("/drivers")
    public ResponseEntity<Driver> update(@RequestBody Driver driver){
        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    // Endpoint para eliminar un driver por su código
    //El nombre de la {code} debe coincidir con el nombre de la variable code de la cabecera
    //si no coincidiera, podríamos ponérselo con @PathVariable("codigo")
    @DeleteMapping("/drivers/{code}")
    public ResponseEntity<Driver> delete(@PathVariable String code){
        this.driverService.deleteDriverByCode(code);
        //si has borrado el dato, ya no existe,
        // por lo que devuelves un estado de "ya no existe ese dato, porque lo has borrado"
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/drivers/listarDrivers"})
    public String listarDrivers(Model model){
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("titulo", "LISTA DE DRIVERS");
        return "listarDrivers";
    }
}

