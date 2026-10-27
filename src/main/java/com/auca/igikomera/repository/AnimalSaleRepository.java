package com.auca.igikomera.repository;


import com.auca.igikomera.model.SaleStatus;
import com.auca.igikomera.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import com.auca.igikomera.model.AnimalSale;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AnimalSaleRepository extends JpaRepository<AnimalSale, UUID> {
    List<AnimalSale> findByStatusOrderBySaleDateAsc(SaleStatus status);

    List<AnimalSale> findBySaleDateBetweenOrderBySaleDateAsc(LocalDate start, LocalDate end);
    boolean existsBySellerAndSaleDateAndStatusNot(Seller seller,LocalDate saleDate,SaleStatus status);

}
