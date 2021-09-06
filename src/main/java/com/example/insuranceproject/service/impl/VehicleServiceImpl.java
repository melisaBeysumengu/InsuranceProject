package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.model.Offer;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    @Override
    public Vehicle findVehicleByChassisNumber(String chassisNumber) {
        return vehicleRepository.findVehicleByChassisNumber(chassisNumber);
    }

    @Override
    public Vehicle createNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Offer> getAllOffers(String chassisNumber) {
        return vehicleRepository.findVehicleByChassisNumber(chassisNumber).getPolicies();
    }

    @Override
    public ResponseEntity<?> addOffer(String chassisNumber, Offer offer) {
        Vehicle v = vehicleRepository.findVehicleByChassisNumber(chassisNumber);
        if(v.getPolicies().contains(offer)){
            return ResponseEntity.ok(new MessageResponse("Bu sigortaya sahip!"));
        }else{
            v.getPolicies().add(offer);
            vehicleRepository.save(v);
        }

        return ResponseEntity.ok(new MessageResponse("Teklif başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(new MessageResponse("Araç bilgileri başarıyla düzenlendi."));
    }

    @Override
    public ResponseEntity<?> deleteVehicle(String chassisNumber) {
        vehicleRepository.deleteVehicleByChassisNumber(chassisNumber);
        return ResponseEntity.ok(new MessageResponse("Araç başarıyla silindi."));
    }
}
