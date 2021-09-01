package com.example.insuranceproject.controller;

import com.example.insuranceproject.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    VehicleService vehicleService;

}
