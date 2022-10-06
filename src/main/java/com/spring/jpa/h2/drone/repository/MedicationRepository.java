package com.spring.jpa.h2.drone.repository;

import com.spring.jpa.h2.drone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicationRepository extends JpaRepository<Medication, String> {

    Medication findByCode(String code);

    List<Medication> findByCodeIn(List<String> code);

}
