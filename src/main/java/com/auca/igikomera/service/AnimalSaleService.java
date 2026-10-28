package com.auca.igikomera.service;

import com.auca.igikomera.model.AnimalSale;
import com.auca.igikomera.model.SaleStatus;
import com.auca.igikomera.repository.AnimalSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.igikomera.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalSaleService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AnimalSaleRepository animalSaleRepository;
    public String save(AnimalSale animalSale)  {

        if (animalSale.getSaleDate().isBefore(LocalDate.now())){
            return "Animal sale date cannot be in the past";

        }
        if (animalSaleRepository.existsBySellerAndSaleDateAndStatusNot(
                animalSale.getSeller(), animalSale.getSaleDate(),SaleStatus.WITHDRAWN)){

              return "Seller already has a sale on that date";
        }
        animalSale.setStatus(SaleStatus.INQUIRED);
        animalSaleRepository.save(animalSale);

        return "Animal sale saved successfully";
    }
    public List<AnimalSale> getAll(){
        return animalSaleRepository.findAll();

    }
    public Optional<AnimalSale> getById(UUID id){
        return animalSaleRepository.findById(id);

    }

    public String update(UUID id, AnimalSale animalSale) {
        Optional<AnimalSale> existing = animalSaleRepository.findById(id);
        if (existing.isEmpty()) {
            return "The animal sale with that id does not exist";
        }
        animalSale.setId(id);
        animalSale.setStatus(existing.get().getStatus());
        animalSaleRepository.save(animalSale);
        return "Animal sale updated successfully";
    }
    public String updateStatus(UUID id, SaleStatus newStatus) {
        Optional<AnimalSale> existing = animalSaleRepository.findById(id);
        if (existing.isEmpty()) {
            return "The animal sale with that id does not exist";
        }
        AnimalSale animalSale = existing.get();
        SaleStatus current = animalSale.getStatus();

        boolean allowed = (current == SaleStatus.INQUIRED && (newStatus == SaleStatus.NEGOTIATED || newStatus == SaleStatus.WITHDRAWN))
                || (current == SaleStatus.NEGOTIATED && (newStatus == SaleStatus.SOLD || newStatus == SaleStatus.WITHDRAWN));

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        animalSale.setStatus(newStatus);
        animalSaleRepository.save(animalSale);
        return "Animal sale status updated successfully";
    }
    public String delete(UUID id) {
        Optional<AnimalSale> existing=animalSaleRepository.findById(id);
        if (existing.isEmpty()) {
            return "The animal sale with that id does not exist";
        }
        if(paymentRepository.existsByAnimalSale(existing.get())){
            return  "That animal sale has payments, delete them first";
        }
        animalSaleRepository.deleteById(id);
        return "Animal sale deleted successfully";
    }
    public List<AnimalSale> getByStatus(SaleStatus status) {
        return animalSaleRepository.findByStatusOrderBySaleDateAsc(status);
    }

    public List<AnimalSale> getBetween(LocalDate start, LocalDate end) {
        return animalSaleRepository.findBySaleDateBetweenOrderBySaleDateAsc(start, end);
    }

}
