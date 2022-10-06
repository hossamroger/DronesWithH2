package com.spring.jpa.h2.drone.mapping;

import com.spring.jpa.h2.drone.model.ActiveDrone;
import com.spring.jpa.h2.drone.model.Medication;
import org.springframework.stereotype.Service;

import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneMapper {

    public List<Medication> mapActiveDroneEntityToMedicationDTOResponse(List<ActiveDrone> activeDroneWithMedicationItems) {
        if (activeDroneWithMedicationItems == null)
            return Collections.emptyList();
        return activeDroneWithMedicationItems.stream().map(item -> new Medication(item.getMedicationItems().getCode(), item.getMedicationItems().getName(), item.getMedicationItems().getWeight(), item.getMedicationItems().getImageUrl())).collect(Collectors.toList());
    }


}
