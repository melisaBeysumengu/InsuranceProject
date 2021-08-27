package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Vehicle;

import java.util.Optional;

public interface VehicleService {

    Vehicle findVehicleById(Long id);

    Vehicle createNewVehicle(Vehicle vehicle);

}
