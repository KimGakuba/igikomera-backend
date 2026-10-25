package com.auca.igikomera.service;

import com.auca.igikomera.model.AnimalType;
import com.auca.igikomera.repository.AnimalSaleRepository;
import com.auca.igikomera.repository.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AnimalTypeService {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    public String save (AnimalType animalType){
        animalTypeRepository.save(animalType);
        return "Animl type saved successfully";
    }

    public List<AnimalType> getAll(){
        return animalTypeRepository.findAll();
    }
    public Optional<AnimalType> getById(UUID id){
        return animalTypeRepository.findById(id);
    }
    public String update(UUID id, AnimalType newData) {
        Optional<AnimalType> found = animalTypeRepository.findById(id);
        if (found.isEmpty()) {
            return "The animal type with that id does not exist";
        }
        AnimalType animalType = found.get();
        animalType.setName(newData.getName());
        animalTypeRepository.save(animalType);
        return "Animal type updated successfully";
    }
    public String delete(UUID id) {
        if (!animalTypeRepository.existsById(id)) {
            return "The animal type with that id does not exist";
        }
        animalTypeRepository.deleteById(id);
        return "Animal type deleted successfully.";
    }

    public List<AnimalType>getUnused(){

        return animalTypeRepository.findUnused();
    }

}
