package com.spring.jpa.h2.drone.repository;

import com.spring.jpa.h2.drone.model.ActiveDrone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ActiveDroneRepository extends JpaRepository<ActiveDrone, Integer> {

    Optional<List<ActiveDrone>> findBySerialNumber(String serialNumber);



}
