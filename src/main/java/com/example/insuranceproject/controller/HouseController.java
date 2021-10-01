package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.*;
import com.example.insuranceproject.service.DaskService;
import com.example.insuranceproject.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;
    private final DaskService daskService;

    @GetMapping("/dask/{id}")
    public Dask getDask(@PathVariable Long id) {
        return houseService.getDask(id);
    }

    @GetMapping("/{id}")
    public House getHouse(@PathVariable Long id) {
        return houseService.findHouseById(id);
    }

    @GetMapping("/owner/{id}")
    public Person getHouseOwner(@PathVariable Long id) {
        return houseService.findOwnerById(id);
    }

    @PutMapping("/{id}/{offerId}")
    public ResponseEntity<?> addOffer(@PathVariable Long id, @PathVariable Long offerId) {
        return houseService.addDask(id, daskService.getDaskById(offerId));
    }

    @PutMapping("/")
    public ResponseEntity<?> updateHouse(@Valid @RequestBody House house) {
        return houseService.updateHouse(house);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return houseService.deleteHouse(id);
    }

}
