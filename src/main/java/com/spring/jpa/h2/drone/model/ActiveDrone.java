package com.spring.jpa.h2.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ActiveDrone")
public class ActiveDrone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(max = 100)
    private String serialNumber;

    @Column(name = "MEDICATION_CODE")
    private String medicationCode;

    @Column(name = "LOADING_DATE")
    private Date loadingDate;

    @Column(name = "CURRENT_LOADED_WEIGHT")
    private int currentLoadedWeight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "MEDICATION_CODE", referencedColumnName = "CODE", insertable = false, updatable = false)
    })
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Medication medicationItems;



    public ActiveDrone() {

    }

    public ActiveDrone(String serialNumber, String medicationCode, Date loadingDate, int currentLoadedWeight) {
        this.serialNumber = serialNumber;
        this.medicationCode = medicationCode;
        this.loadingDate = loadingDate;
        this.currentLoadedWeight = currentLoadedWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public int getCurrentLoadedWeight() {
        return currentLoadedWeight;
    }

    public void setCurrentLoadedWeight(int currentLoadedWeight) {
        this.currentLoadedWeight = currentLoadedWeight;
    }

    public Medication getMedicationItems() {
        return medicationItems;
    }

    public void setMedicationItems(Medication medicationItems) {
        this.medicationItems = medicationItems;
    }
}
