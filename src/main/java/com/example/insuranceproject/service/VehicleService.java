package com.example.insuranceproject.service;

import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    Vehicle createNewVehicle(Vehicle vehicle);

    List<BaseOffer> getAllOffers(String chassisNumber);

    ResponseEntity<?> addOffer(String chassisNumber, BaseOffer baseOffer);

    ResponseEntity<?> updateVehicle(Vehicle vehicle);

    ResponseEntity<?> deleteVehicle(String chassisNumber);

}
