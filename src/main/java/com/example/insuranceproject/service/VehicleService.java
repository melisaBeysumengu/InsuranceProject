package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    Vehicle createNewVehicle(Vehicle vehicle);

    List<Kasko> getAllOffers(String chassisNumber);

    ResponseEntity<?> addOffer(String chassisNumber, Kasko kasko);

    ResponseEntity<?> updateVehicle(Vehicle vehicle);

    ResponseEntity<?> deleteVehicle(String chassisNumber);

}
