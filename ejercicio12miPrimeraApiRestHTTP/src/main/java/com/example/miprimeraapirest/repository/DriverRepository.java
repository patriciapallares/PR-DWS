package com.example.miprimeraapirest.repository;

import com.example.miprimeraapirest.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByCodeIgnoreCase(String code);


    void deleteDriverByCode(String code);
}
