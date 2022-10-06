package com.spring.jpa.h2.drone.dto;

public enum DroneState {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    public String droneState;

    DroneState(String droneState) {
        this.droneState = droneState;
    }
}
