package com.auca.igikomera.repository;


import com.auca.igikomera.model.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StallRepository extends JpaRepository<Stall, UUID> {
    @Query("SELECT l FROM Stall l WHERE NOT EXISTS (SELECT p FROM Seller p WHERE p.stall = l) ORDER BY l.stallNumber ASC")
    List<Stall> findFreeStalls();
}
