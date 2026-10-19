package com.auca.igikomera.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.service.AnimalSaleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import java.util.Optional;
import com.auca.igikomera.model.AnimalSale;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/animal-sales")
public class AnimalSaleController {
    @Autowired
    private AnimalSaleService animalSaleService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AnimalSale animalSale) {
        String message = animalSaleService.save(animalSale);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(animalSaleService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<AnimalSale> animalSale = animalSaleService.getById(id);
        if (animalSale.isPresent()) {
            return new ResponseEntity<>(animalSale.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The animal sale with that id is not exist", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AnimalSale animalSale) {
        String message = animalSaleService.update(id, animalSale);
        if (message.equals("The animal sale with that id doesn't exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = animalSaleService.delete(id);
        if (message.equals("The animal sale with that id doesn't exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}