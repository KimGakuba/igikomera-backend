package com.auca.igikomera.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.service.BuyerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import java.util.Optional;
import com.auca.igikomera.model.Buyer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Buyer buyer) {
        String message = buyerService.save(buyer);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(buyerService.getAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = buyerService.delete(id);
        if (message.equals("The buyer with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Buyer> buyer = buyerService.getById(id);
        if (buyer.isPresent()) {
            return new ResponseEntity<>(buyer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The buyer with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Buyer buyer) {
        String message = buyerService.update(id, buyer);
        if (message.equals("Buyer updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
