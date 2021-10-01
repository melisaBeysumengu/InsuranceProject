package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.service.PersonService;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final VehicleService vehicleService;

    @GetMapping("/")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{tcNumber}")
    public Person getPersonByTcNumber(@PathVariable("tcNumber") Integer tcNumber) {
        return personService.findByTcNo(tcNumber);
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/{tcNumber}")
    public ResponseEntity<?> deletePerson(@PathVariable("tcNumber") Integer tcNumber) {
        return personService.deletePerson(tcNumber);
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewPerson(@Valid @RequestBody Person person) {
        return personService.createNewPerson(person);
    }

    @PutMapping("/house/{tcNumber}")
    public ResponseEntity<?> addHouse(@PathVariable("tcNumber") Integer tcNumber, @Valid @RequestBody House house) {
        return personService.addHouse(tcNumber, house);
    }

    @PutMapping("/vehicle/{tcNumber}")
    public ResponseEntity<?> addVehicle(@PathVariable("tcNumber") Integer tcNumber,
                                        @Valid @RequestBody Vehicle vehicle) {
        vehicleService.createNewVehicle(vehicle);
        return personService.addVehicle(tcNumber, vehicle);
    }

    @GetMapping("/vehicle/{tcNumber}")
    public List<Vehicle> getVehicles(@PathVariable("tcNumber") Integer tcNumber) {
        return personService
                .findByTcNo(tcNumber)
                .getVehicles();
    }

    @GetMapping("/house/{tcNumber}")
    public List<House> getHouses(@PathVariable("tcNumber") Integer tcNumber) {
        return personService
                .findByTcNo(tcNumber)
                .getHouses();
    }



}
