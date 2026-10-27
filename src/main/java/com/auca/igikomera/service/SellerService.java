package com.auca.igikomera.service;

import com.auca.igikomera.model.AnimalType;
import com.auca.igikomera.model.Stall;
import com.auca.igikomera.repository.AnimalTypeRepository;
import com.auca.igikomera.repository.StallRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.model.Seller;
import com.auca.igikomera.repository.SellerRepository;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private StallRepository stallRepository;
    public String save(Seller seller) {
        sellerRepository.save(seller);
        return "Seller saved successfully";
    }

    public List<Seller> getAll() {
        return sellerRepository.findAll();

    }
    public Optional<Seller> getById(UUID id) {
        return sellerRepository.findById(id);

    }
    public String update(UUID id, Seller newData) {
        Optional<Seller> found = sellerRepository.findById(id);
        if (found.isEmpty()) {
            return "The seller with that id does not exist";
        }
        Seller seller = found.get();
        seller.setFirstName(newData.getFirstName());
        seller.setLastName(newData.getLastName());
        seller.setPhone(newData.getPhone());
        seller.setFarmLocation(newData.getFarmLocation());
        seller.setStall(newData.getStall());
        seller.setAnimalTypes(newData.getAnimalTypes());
        sellerRepository.save(seller);
        return "Seller updated successfully";
    }

    public String delete(UUID id) {
        if (!sellerRepository.existsById(id)) {
            return "The seller with that id does not exist";
        }
        sellerRepository.deleteById(id);
        return "Seller deleted succesfully";
    }
    public List<Seller> getByAnimalType(String name){

        return sellerRepository.findByAnimalTypeName(name);
    }
    public String addAnimalType(UUID sellerId,UUID animalTypeId){
        Optional<Seller>sellerOpt= sellerRepository.findById(sellerId);
        if (sellerOpt.isEmpty()){
            return "The seller with that id does not exist";
        }
        Optional<AnimalType>animalTypeOpt =animalTypeRepository.findById(animalTypeId);
        if (animalTypeOpt.isEmpty()) {
            return "That animal type does not exist";
        }
        Seller seller =sellerOpt.get();
        AnimalType animalType = animalTypeOpt.get();
        boolean alreadyHasIt = seller.getAnimalTypes().stream()
                .anyMatch(existing -> existing.getId().equals(animalType.getId()));
        if (alreadyHasIt){
            return "The seller already has that animal type";
        }
        seller.getAnimalTypes().add(animalType);
        sellerRepository.save(seller);

        return "Animal type added to the seller";
    }
    public String changeStall(UUID sellerId,int stallNumber){
        Optional<Seller>sellerOpt =sellerRepository.findById(sellerId);
        if (sellerOpt.isEmpty()) {
            return "The seller with that id does not exist";
        }
        Optional<Stall>stallOpt= stallRepository.findByStallNumber(stallNumber) ;
        if (stallOpt.isEmpty()){
            return "The stall with that stallNumber does not exist";
        }
        Seller seller = sellerOpt.get();

        Stall stall = stallOpt.get();
        boolean sameStall =seller.getStall()!=null && seller.getStall().getId().equals(stall.getId());
        if (!sameStall && sellerRepository.existsByStall(stall)){
            return "That stall is taken by another seller";

        }

        seller.setStall(stall);
        sellerRepository.save(seller);
        return "Stall changed successfully";

    }
}