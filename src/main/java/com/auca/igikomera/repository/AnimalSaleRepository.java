package com.auca.igikomera.repository;


import com.auca.igikomera.model.SaleStatus;
import com.auca.igikomera.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import com.auca.igikomera.model.AnimalSale;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AnimalSaleRepository extends JpaRepository<AnimalSale, UUID> {
    List<AnimalSale> findByStatusOrderBySaleDateAsc(SaleStatus status);

    List<AnimalSale> findBySaleDateBetweenOrderBySaleDateAsc(LocalDate start, LocalDate end);
    boolean existsBySellerAndSaleDateAndStatusNot(Seller seller,LocalDate saleDate,SaleStatus status);
    @Query("SELECT a.status, COUNT(a) FROM AnimalSale a GROUP BY a.status")
    List<Object[]>countByStatus();
    @Modifying
    @Query("UPDATE AnimalSale a SET a.status = com.auca.igikomera.model.SaleStatus.WITHDRAWN "
            + "WHERE a.seller.id = :sellerId AND a.saleDate = :date AND a.status <> com.auca.igikomera.model.SaleStatus.SOLD")
    int cancelDay(@Param("sellerId")UUID sellerId,@Param("date")LocalDate date);

    long countBySaleDate(LocalDate saleDate);
    long countByStatus(SaleStatus status);

}
