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
    public String update(UUID id, Seller seller) {
        if (!sellerRepository.existsById(id)) {
            return "The seller with that id does not exist";
        }
        seller.setId(id);
        sellerRepository.save(seller);
        return "Seller updated successfully";
    }

    public String delete(UUID id) {
        if (!sellerRepository.existsById(id)) {
            return "Seller's id provided doesn't exist ";
        }
        sellerRepository.deleteById(id);
        return "Seller deleted succesfully";
    }
}

