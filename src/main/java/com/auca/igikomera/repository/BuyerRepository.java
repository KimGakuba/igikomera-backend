package com.auca.igikomera.repository;


import com.auca.igikomera.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    List<Buyer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
