package com.auca.igikomera.repository;


import com.auca.igikomera.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, UUID> {
}
