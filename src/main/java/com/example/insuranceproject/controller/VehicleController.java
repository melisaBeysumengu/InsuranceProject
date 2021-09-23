package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.service.OfferService;
import com.example.insuranceproject.service.PersonService;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    VehicleService vehicleService;

    OfferService offerService;

    PersonService personService;

    //private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

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
        //return personService.findVehicleByChassisNumber(chassisNumber);
    }

    @PutMapping("/{chassisNumber}/{offerId}")
    public ResponseEntity<?> addOffer(@PathVariable String chassisNumber, @PathVariable Long offerId) {
        return vehicleService.addOffer(chassisNumber, offerService.getOfferById(offerId));
    }

    @DeleteMapping("/{chassisNumber}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String chassisNumber) {
        return vehicleService.deleteVehicle(chassisNumber);
    }

}
