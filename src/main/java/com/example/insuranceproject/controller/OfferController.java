package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.service.OfferService;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final VehicleService vehicleService;

    @GetMapping("/")
    public List<Kasko> getAll(){return offerService.getAll();}

    @GetMapping("/category/{category}/{chassisNumber}")
    public Kasko getOffersByCategory(@PathVariable("category") String category,
                                           @PathVariable String chassisNumber){
        Vehicle v = vehicleService.findVehicleByChassisNumber(chassisNumber);
        return offerService.getOffersByCategory(category, v.getAge(), v.getKilometer());
    }

    @GetMapping("/{id}")
    public Kasko getOfferById(@PathVariable("id") Long id){
        return offerService.getOfferById(id);
    }

    @PostMapping("/")
    public Kasko createNewOffer(@Valid @RequestBody Kasko kasko){
        return offerService.createNewOffer(kasko);
    }

}
