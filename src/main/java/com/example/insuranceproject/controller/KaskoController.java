package com.example.insuranceproject.controller;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.service.KaskoService;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/kasko")
public class KaskoController {

    private final KaskoService kaskoService;

    @GetMapping("/")
    public Kasko getAll(){return kaskoService.getKaskoByCategory();}

    @GetMapping("/{id}")
    public Kasko getKaskoById(@PathVariable("id") Long id){
        return kaskoService.getKaskoById(id);
    }

    @PostMapping("/")
    public Kasko createNewOffer(@Valid @RequestBody Kasko kasko){
        return kaskoService.createNewKasko(kasko);
    }

    @GetMapping("/all")
    public List<AllOffersResponseDTO> getAllKaskos(){return kaskoService.gelAllKaskos();}
}
