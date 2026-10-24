package com.auca.igikomera.controller;

import com.auca.igikomera.model.SaleStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.service.AnimalSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import com.auca.igikomera.model.AnimalSale;

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
        if (message.equals("Animal sale updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = animalSaleService.delete(id);
        if (message.equals("The animal sale with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/by-status")
    public ResponseEntity<List<AnimalSale>> getByStatus(@RequestParam SaleStatus status) {
        return new ResponseEntity<>(animalSaleService.getByStatus(status), HttpStatus.OK);
    }
    @GetMapping("/between")
    public ResponseEntity<List<AnimalSale>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(animalSaleService.getBetween(startDate, endDate), HttpStatus.OK);
    }
}