package com.spring.jpa.h2.drone.dto;

public enum DroneModel {
    LIGHT_WEIGHT("LIGHTWEIGHT"),
    MIDDLE_WEIGHT("MIDDLEWEIGHT"),
    CRUISER_WEIGHT("CRUISERWEIGHT"),
    HEAVY_WEIGHT("HEAVYWEIGHT");

    public String droneModel;

    DroneModel(String droneModel) {
        this.droneModel = droneModel;
    }
}
