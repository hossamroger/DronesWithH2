package com.spring.jpa.h2.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "drone")
public class Drone {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Length(max = 100)
    private String serialNumber ;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "WEIGHT_LIMIT")
    @Max(500)
    @Min(1)
    private int weightLimit;

    @Column(name = "BATTERY_CAPACITY")
    @Max(100)
    @Min(0)
    private int batteryCapacity;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CURRENT_LOADED_WEIGHT")
    private int currentLoadedWeight;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "serialNumber")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<ActiveDrone> activeDrones;


    public Drone() {
    }

    public Drone( String model, int weightLimit, int batteryCapacity, String state) {
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone( String serialNumber, String model,  int weightLimit,int batteryCapacity, String state, int currentLoadedWeight) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.currentLoadedWeight = currentLoadedWeight;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCurrentLoadedWeight() {
        return currentLoadedWeight;
    }

    public void setCurrentLoadedWeight(int currentLoadedWeight) {
        this.currentLoadedWeight = currentLoadedWeight;
    }

    public List<ActiveDrone> getActiveDrones() {
        return activeDrones;
    }

    public void setActiveDrones(List<ActiveDrone> activeDrones) {
        this.activeDrones = activeDrones;
    }
}
