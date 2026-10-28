package com.auca.igikomera.repository;

import com.auca.igikomera.model.AnimalSale;
import com.auca.igikomera.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    @Query("SELECT p FROM Payment p WHERE  p.animalSale.buyer.nationalId=:nationalId ORDER BY p.paidDate DESC")
    List<Payment>findByBuyerNationalId(@Param("nationalId")String nationalId);
    boolean existsByAnimalSale(AnimalSale animalSale);
}
