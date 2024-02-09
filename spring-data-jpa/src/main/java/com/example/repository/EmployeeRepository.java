package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Optional devuelve sólo uno
    Optional<Employee> findByFullName(String fullName);
    Optional<Employee> findByFullNameAndBirthDate(String fullName, LocalDate birthDate);

    List<Employee> findAllByBirthDateAfter(LocalDate birthDate);
    List<Employee> findAllByBirthDateBetween(LocalDate min, LocalDate max);

    // cuantos menos parámetros tengamos mejor
    List<Employee> findAllByMarriedTrue();
    List<Employee> findAllByMarriedFalse();
}
