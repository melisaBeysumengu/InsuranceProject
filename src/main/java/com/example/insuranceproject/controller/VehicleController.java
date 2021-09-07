package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.service.OfferService;
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

    //private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping("/{chassisNumber}")
    public List<BaseOffer> getAllOffers(@PathVariable String chassisNumber) {
        return vehicleService.getAllOffers(chassisNumber);
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
