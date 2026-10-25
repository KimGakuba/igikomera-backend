package com.auca.igikomera.repository;


import com.auca.igikomera.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    List<Buyer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT DISTINCT a.buyer FROM " +
            "AnimalSale a WHERE a.seller.id = :sellerId")
    List<Buyer>  findBuyersOfSeller(@Param("sellerId")UUID sellerId);
}
