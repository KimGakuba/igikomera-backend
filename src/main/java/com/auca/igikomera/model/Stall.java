package com.auca.igikomera.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;

@Entity
@Table(name = "stall")
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer stallNumber;
    private String name;

    public Stall() {
    }

    public Stall(UUID id, Integer stallNumber, String name) {
        this.id = id;
        this.stallNumber = stallNumber;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStallNumber() {
        return stallNumber;
    }

    public void setStallNumber(Integer stallNumber) {
        this.stallNumber = stallNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}