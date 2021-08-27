package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    @Override
    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findVehicleById(id);
    }

    @Override
    public Vehicle createNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
