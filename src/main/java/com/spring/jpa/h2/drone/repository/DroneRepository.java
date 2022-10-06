package com.spring.jpa.h2.drone.repository;

import java.util.List;
import java.util.Optional;

import com.spring.jpa.h2.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DroneRepository extends JpaRepository<Drone, String> {
    Optional<Drone> findBySerialNumber(String serialNumber);

    Optional<List<Drone>> findDistinctByStateAndBatteryCapacityGreaterThanAndActiveDrones_Empty(String state,int level);
}
