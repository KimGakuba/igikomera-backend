package com.auca.igikomera.service;

import com.auca.igikomera.model.AnimalSale;
import com.auca.igikomera.model.Payment;
import com.auca.igikomera.model.SaleStatus;
import com.auca.igikomera.repository.AnimalSaleRepository;
import com.auca.igikomera.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AnimalSaleRepository animalSaleRepository;

    public String save(UUID animalSaleId, Payment payment){
        Optional<AnimalSale>animalSaleOpt= animalSaleRepository.findById(animalSaleId);
        if(animalSaleOpt.isEmpty()){
            return "The animal sale with that id does not exist";

        }
        AnimalSale animalSale = animalSaleOpt.get();
        if(animalSale.getStatus()!= SaleStatus.SOLD){
            return "Payments are only for SOLD animal sales";

        }
        payment.setPaidDate(LocalDate.now());
        payment.setAnimalSale(animalSale);

        paymentRepository.save(payment);
        return "Payment saved successfully";


    }
    public List<Payment> getBuyerNationalId(String nationalId){
        return paymentRepository.findByBuyerNationalId(nationalId);
    }
    public String delete(UUID id){
        if(!paymentRepository.existsById(id)){
            return "The payment with that id does not exist";

        }
        paymentRepository.deleteById(id);
        return "Payment deleted successfully";
    }
}
