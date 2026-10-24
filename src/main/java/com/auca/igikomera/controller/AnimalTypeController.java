package com.auca.igikomera.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.auca.igikomera.service.AnimalTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import java.util.Optional;
import com.auca.igikomera.model.AnimalType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/animal-types")
public class AnimalTypeController {
    @Autowired
    private AnimalTypeService animalTypeService;
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(animalTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<AnimalType> animalType = animalTypeService.getById(id);
        if (animalType.isPresent()) {
            return new ResponseEntity<>(animalType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The animal type with that id does not exist", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = animalTypeService.delete(id);
        if (message.equals("The animal type with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AnimalType animalType) {
        String message = animalTypeService.update(id, animalType);
        if (message.equals("Animal type updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AnimalType animalType) {
        String message = animalTypeService.save(animalType);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

}
