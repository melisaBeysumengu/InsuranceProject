package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.model.CarInsurance;
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
    public List<BaseOffer> getAll(){return offerService.getAll();}

    @GetMapping("/category/{category}/{chassisNumber}")
    public List<BaseOffer> getOffersByCategory(@PathVariable("category") String category,
                                               @PathVariable String chassisNumber){
        Vehicle v = vehicleService.findVehicleByChassisNumber(chassisNumber);
        return offerService.getOffersByCategory(category, v.getAge(), v.getKilometer());
    }

    @GetMapping("/{id}")
    public BaseOffer getOfferById(@PathVariable("id") Long id){
        return offerService.getOfferById(id);
    }

    @PostMapping("/")
    public BaseOffer createNewOffer(@Valid @RequestBody CarInsurance carInsurance){
        return offerService.createNewOffer(carInsurance);
    }

}
