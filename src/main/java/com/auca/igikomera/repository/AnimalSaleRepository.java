package com.auca.igikomera.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.auca.igikomera.model.AnimalSale;
import java.util.UUID;

public interface AnimalSaleRepository extends JpaRepository<AnimalSale, UUID> {

}
