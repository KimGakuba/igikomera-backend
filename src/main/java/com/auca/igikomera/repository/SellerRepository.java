package com.auca.igikomera.repository;


import com.auca.igikomera.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
    @Query("SELECT DISTINCT s FROM Seller s JOIN s.animalTypes a WHERE LOWER(a.name) = LOWER(:name)")
    List<Seller>findByAnimalTypeName(@Param("name")String name);
}
