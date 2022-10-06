package com.spring.jpa.h2.drone.dto.request;

import java.util.List;

public class LoadDroneDTO {
    private String droneSerialNumber;
    private List<String> medicationItemsCodes;

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }


    public List<String> getMedicationItemsCodes() {
        return medicationItemsCodes;
    }

    public void setMedicationItemsCodes(List<String> medicationItemsCodes) {
        this.medicationItemsCodes = medicationItemsCodes;
    }
}
