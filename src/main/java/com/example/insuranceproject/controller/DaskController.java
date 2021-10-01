package com.example.insuranceproject.controller;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.*;
import com.example.insuranceproject.service.DaskService;
import com.example.insuranceproject.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/dask")
public class DaskController {

    private final DaskService daskService;

    @GetMapping("/")
    public Dask getOriginal(){return daskService.getOriginalDask();}

    @GetMapping("/{id}")
    public Dask getDaskById(@PathVariable("id") Long id){
        return daskService.getDaskById(id);
    }

    @PostMapping("/")
    public Dask createNewDask(@Valid @RequestBody Dask dask){
        return daskService.createNewDask(dask);
    }

    @GetMapping("/all")
    public List<AllOffersResponseDTO> getAllDasks(){return daskService.gelAllDasks();}
}
