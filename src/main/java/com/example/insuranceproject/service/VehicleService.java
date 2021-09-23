package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.http.ResponseEntity;

public interface VehicleService {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    void createNewVehicle(Vehicle vehicle);

    Kasko getAllOffers(String chassisNumber);

    ResponseEntity<?> addOffer(String chassisNumber, Kasko kasko);

    ResponseEntity<?> updateVehicle(Vehicle vehicle);

    ResponseEntity<?> deleteVehicle(String chassisNumber);

    Person findOwnerByChassisNumber(String chassisNumber);

}
