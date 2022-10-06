package com.spring.jpa.h2.drone.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "DroneLog")
public class DroneLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "DRONE_SERIAL_NUMBER")
    private String droneSerialNumber ;

    @Column(name = "MEDICATION_CODE")
    private String MedicationCode;

    @Column(name = "LOADING_DATE")
    private Date loadingDate;

    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

    public DroneLog() {

    }

    public DroneLog(String droneSerialNumber, String medicationCode, Date loadingDate, Date deliveryDate) {
        this.droneSerialNumber = droneSerialNumber;
        MedicationCode = medicationCode;
        this.loadingDate = loadingDate;
        this.deliveryDate = deliveryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public String getMedicationCode() {
        return MedicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        MedicationCode = medicationCode;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
