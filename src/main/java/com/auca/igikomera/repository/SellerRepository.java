package com.auca.igikomera.repository;


import com.auca.igikomera.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
