package com.auca.igikomera.service;

import com.auca.igikomera.model.AnimalSale;
import com.auca.igikomera.repository.AnimalSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalSaleService {
    @Autowired
    private AnimalSaleRepository animalSaleRepository;
    public String save(AnimalSale animalSale){
        animalSaleRepository.save(animalSale);
        return "Animal sale saved successfully";
    }
    public List<AnimalSale> getAll(){
        return animalSaleRepository.findAll();

    }
    public Optional<AnimalSale> getById(UUID id){
        return animalSaleRepository.findById(id);

    }

    public String update(UUID id, AnimalSale animalSale){
        if (!animalSaleRepository.existsById(id)){
            return "The animal sale with that id does not exist";
        }
        animalSale.setId(id);
        return "Animal sale deleted successfuly";
    }
    public String delete(UUID id) {
        if (!animalSaleRepository.existsById(id)) {
            return "The animal sale with that id does not exist";
        }
        animalSaleRepository.deleteById(id);
        return "Animal sale deleted successfully";
    }

}
