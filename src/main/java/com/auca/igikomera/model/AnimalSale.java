package com.auca.igikomera.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "animal_sale")
public class AnimalSale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate saleDate;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private String breed;
    private double askingPrice;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;


    //default const

    public AnimalSale() {
    }
    //param const

    public AnimalSale(UUID id, LocalDate saleDate, SaleStatus status, String breed, double askingPrice, Buyer buyer, Seller seller) {
        this.id = id;
        this.saleDate = saleDate;
        this.status = status;
        this.breed = breed;
        this.askingPrice = askingPrice;
        this.buyer = buyer;
        this.seller = seller;
    }
    //getters and seters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
