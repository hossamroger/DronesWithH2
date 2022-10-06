package com.spring.jpa.h2.drone.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(generator = "med-uuid")
    @GenericGenerator(name = "med-uuid", strategy = "uuid")
    @Length(max = 100)
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "image")
    private String imageUrl;

    public Medication() {

    }

    public Medication(String name, int weight, String imageUrl) {
        this.name = name;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }


    public Medication(String code, String name, int weight, String imageUrl) {
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
