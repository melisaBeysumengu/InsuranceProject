package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Offer;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    Vehicle createNewVehicle(Vehicle vehicle);

    List<Offer> getAllOffers(String chassisNumber);

    ResponseEntity<?> addOffer(String chassisNumber, Offer offer);

    ResponseEntity<?> updateVehicle(Long id, Vehicle vehicle);

    ResponseEntity<?> deleteVehicle(Long id);

}
