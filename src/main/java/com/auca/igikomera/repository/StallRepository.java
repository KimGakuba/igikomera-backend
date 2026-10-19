package com.auca.igikomera.repository;


import com.auca.igikomera.model.Stall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StallRepository extends JpaRepository<Stall, UUID> {
}
