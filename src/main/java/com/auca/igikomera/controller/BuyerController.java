package com.auca.igikomera.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.service.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import com.auca.igikomera.model.Buyer;

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
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Buyer>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(buyerService.getByLastName(lastName), HttpStatus.OK);
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
