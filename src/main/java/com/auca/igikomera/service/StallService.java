package com.auca.igikomera.service;


import com.auca.igikomera.model.Stall;
import com.auca.igikomera.repository.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StallService {
    @Autowired
    private StallRepository stallRepository;

    public String save(Stall stall) {
        stallRepository.save(stall);
        return "Stall saved successfully";
    }

    public List<Stall> getAll() {
        return stallRepository.findAll();

    }
    public Optional<Stall> getById(UUID id) {
        return stallRepository.findById(id);
    }
    public String update(UUID id, Stall newData) {
        Optional<Stall> found = stallRepository.findById(id);
        if (found.isEmpty()) {
            return "The stall with that id does not exist";
        }
        Stall stall = found.get();
        stall.setStallNumber(newData.getStallNumber());
        stall.setName(newData.getName());
        stallRepository.save(stall);
        return "Stall updated successfully";
    }
    public String delete(UUID id) {
        if (!stallRepository.existsById(id)) {
            return "The stall with that id does not exist";
        }
        stallRepository.deleteById(id);
        return "Stall delleted successfully";
    }
}

