package com.auca.igikomera.service;

import com.auca.igikomera.model.Buyer;
import com.auca.igikomera.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    public String save (Buyer buyer){
        buyerRepository.save(buyer);
        return "Buyer saved succesfully";

    }
    public List<Buyer> getAll(){
        return buyerRepository.findAll();

    }

    public Optional<Buyer> getById(UUID id){
        return buyerRepository.findById(id);

    }
    public String update(UUID id, Buyer newData) {
        Optional<Buyer> found = buyerRepository.findById(id);
        if (found.isEmpty()) {
            return "The buyer with that id does not exist";
        }
        Buyer buyer = found.get();
        buyer.setFirstName(newData.getFirstName());
        buyer.setLastName(newData.getLastName());
        buyer.setNationalId(newData.getNationalId());
        buyer.setPhone(newData.getPhone());
        buyer.setDistrict(newData.getDistrict());
        buyerRepository.save(buyer);
        return "Buyer updated successfully";
    }
    public String delete(UUID id) {
        if (!buyerRepository.existsById(id)) {
            return "The buyer with that id does not exist";
        }
        buyerRepository.deleteById(id);
        return "Buyer deleted successfully";
    }

    public List<Buyer> getByLastName(String lastName) {
        return buyerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

}
