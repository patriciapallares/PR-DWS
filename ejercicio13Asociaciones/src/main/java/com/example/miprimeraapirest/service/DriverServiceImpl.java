package com.example.miprimeraapirest.service;

import com.example.miprimeraapirest.model.Driver;
import com.example.miprimeraapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository repository;

    @Autowired
    public DriverServiceImpl(DriverRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Driver> getAllDrivers() {
        return repository.findAll();
    }

    @Override
    public Optional<Driver> getDriverByCode(String code) {
        return repository.findByCodeIgnoreCase(code);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        this.repository.save(driver);
        return driver;
    }

    @Override
    public void deleteDriverByCode(String code){
        this.repository.deleteDriverByCode(code);
    }

}
