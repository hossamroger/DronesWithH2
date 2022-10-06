package com.spring.jpa.h2.drone.repository;

import com.spring.jpa.h2.drone.model.DroneLog;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DroneLogRepository extends JpaRepository<DroneLog, Integer> {

}
