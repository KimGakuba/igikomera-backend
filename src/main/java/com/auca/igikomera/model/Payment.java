package com.auca.igikomera.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="payment")
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String method;
    private String reference;
    private int amountRwf;
    private LocalDate paidDate;
    @ManyToOne
    @JoinColumn(name ="animal_sale_id")
    private AnimalSale animalSale;

    //const's
    public Payment() {
    }

    public Payment(UUID id, String method, String reference, int amountRwf, LocalDate paidDate, AnimalSale animalSale) {
        this.id = id;
        this.method = method;
        this.reference = reference;
        this.amountRwf = amountRwf;
        this.paidDate = paidDate;
        this.animalSale = animalSale;
    }
    //geters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public AnimalSale getAnimalSale() {
        return animalSale;
    }

    public void setAnimalSale(AnimalSale animalSale) {
        this.animalSale = animalSale;
    }
}
