package com.auca.igikomera.service;

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
}

