package com.auca.igikomera.repository;


import com.auca.igikomera.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, UUID> {

    @Query("SELECT a FROM AnimalType a WHERE a.sellers IS EMPTY")
    List<AnimalType>findUnused();
}
