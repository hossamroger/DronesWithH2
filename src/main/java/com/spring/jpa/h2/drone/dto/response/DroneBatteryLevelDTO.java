package com.spring.jpa.h2.drone.dto.response;

public class DroneBatteryLevelDTO {
    private int battryLevel;

    public DroneBatteryLevelDTO(int battryLevel) {
        this.battryLevel = battryLevel;
    }

    public int getBattryLevel() {
        return battryLevel;
    }

    public void setBattryLevel(int battryLevel) {
        this.battryLevel = battryLevel;
    }
}
