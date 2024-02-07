package com.example.miprimeraapirest.controller;

import com.example.miprimeraapirest.model.Driver;
import com.example.miprimeraapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *   http://localhost:8080/api/
 * */
@RestController
@RequestMapping("/api")
public class DriverRestController {
    private final DriverService driverService;


    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    /*
     *   http://localhost:8080/api/drivers
     * */
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAll(){
        return ResponseEntity.ok(driverService.getAllDrivers());
    }
}
