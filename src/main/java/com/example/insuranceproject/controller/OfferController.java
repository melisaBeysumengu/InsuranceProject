package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.Offer;
import com.example.insuranceproject.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/")
    public List<Offer> getAll(){return offerService.getAll();}

    @GetMapping("/category/{category}")
    public List<Offer> getOffersByCategory(@PathVariable("category") String category){
        return offerService.getOffersByCategory(category);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long id){
        return offerService.getOfferById(id);
    }

    @PostMapping("/")
    public Offer createNewOffer(@Valid @RequestBody Offer offer){
        return offerService.createNewOffer(offer);
    }

}
