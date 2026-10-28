package com.auca.igikomera.controller;

import com.auca.igikomera.model.Payment;
import com.auca.igikomera.repository.PaymentRepository;
import com.auca.igikomera.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID animalSaleId, @RequestBody Payment payment){
        String message = paymentService.save(animalSaleId,payment);
        if(message.equals("The animal sale with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }
        if(message.equals("Payments are only for SOLD animal sales")){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }
    @GetMapping("/buyer/{nationalId}")
    public ResponseEntity<List<Payment>> getBuyerNationalId(@PathVariable String nationalId){
        return new ResponseEntity<>(paymentService.getBuyerNationalId(nationalId),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message= paymentService.delete(id);
        if(message.equals("The payment with that id does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}