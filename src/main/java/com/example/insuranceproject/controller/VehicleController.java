package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.service.KaskoService;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    private final KaskoService kaskoService;

    @GetMapping("/offers/{chassisNumber}")
    public Kasko getAllOffers(@PathVariable String chassisNumber) {
        return vehicleService.getAllOffers(chassisNumber);
    }

    @GetMapping("/{chassisNumber}")
    public Vehicle getVehicle(@PathVariable String chassisNumber) {
        return vehicleService.findVehicleByChassisNumber(chassisNumber);
    }

    @GetMapping("/owner/{chassisNumber}")
    public Person getVehicleOwner(@PathVariable String chassisNumber) {
        return vehicleService.findOwnerByChassisNumber(chassisNumber);
    }

    @PutMapping("/{chassisNumber}/{offerId}")
    public ResponseEntity<?> addOffer(@PathVariable String chassisNumber, @PathVariable Long offerId) {
        return vehicleService.addOffer(chassisNumber, kaskoService.getKaskoById(offerId));
    }

    @DeleteMapping("/{chassisNumber}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String chassisNumber) {
        return vehicleService.deleteVehicle(chassisNumber);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

}
