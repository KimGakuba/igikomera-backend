package com.auca.igikomera.controller;

import com.auca.igikomera.model.Seller;
import com.auca.igikomera.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Seller seller) {
        String message = sellerService.save(seller);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Seller> seller = sellerService.getById(id);
        if (seller.isPresent()) {
            return new ResponseEntity<>(seller.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The seller with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Seller seller) {
        String message = sellerService.update(id, seller);
        if (message.equals("Seller updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = sellerService.delete(id);
        if (message.equals("The seller with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/by-animal-type")
    public ResponseEntity<List<Seller>>getByAnimalType(@RequestParam String name){
        return new ResponseEntity<> (sellerService.getByAnimalType(name),HttpStatus.OK);
    }

    @PatchMapping("/{sellerId}/stall")
    public ResponseEntity<String> changeStall(@PathVariable UUID sellerId,@RequestParam int stallNumber){

        String message =sellerService.changeStall(sellerId,stallNumber);
        if(message.equals("The seller with that id does not exist") || message.equals("The stall with that stallNumber does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
        if(message.equals("That stall is taken by another seller")){

            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message,   HttpStatus.OK);
    }


    @PutMapping("/{sellerId}/animal-types/{animalTypeId}")
    public ResponseEntity<String>addAnimalType(@PathVariable UUID sellerId,@PathVariable UUID animalTypeId){

        String message=  sellerService.addAnimalType(sellerId,animalTypeId);
        if (message.equals ("The seller with that id does not exist") || message.equals("That animal type does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
        if (message.equals("The seller already has that animal type")){
            return new ResponseEntity<>(message,  HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message,HttpStatus.OK);

    }
}