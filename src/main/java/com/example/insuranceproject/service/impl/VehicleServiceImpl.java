package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.model.CarInsurance;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.CarInsuranceRepository;
import com.example.insuranceproject.repository.OfferRepository;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;
    OfferRepository offerRepository;
    CarInsuranceRepository carInsuranceRepository;

    @Override
    public Vehicle findVehicleByChassisNumber(String chassisNumber) {
        return vehicleRepository.findVehicleByChassisNumber(chassisNumber);
    }

    @Override
    public Vehicle createNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<BaseOffer> getAllOffers(String chassisNumber) {
        return vehicleRepository
                .findVehicleByChassisNumber(chassisNumber)
                .getPolicies();
    }

    @Override
    public ResponseEntity<?> addOffer(String chassisNumber, BaseOffer baseOffer) {
        Vehicle v = vehicleRepository.findVehicleByChassisNumber(chassisNumber);
        CarInsurance c = (CarInsurance) baseOffer;
        c.setPrice(baseOffer.getPrice(), v.getAge(), v.getKilometer());
        if (v
                .getPolicies()
                .contains(c)) {
            return ResponseEntity.ok(new MessageResponse("Bu sigortaya sahip!"));
        } else {
            carInsuranceRepository.save(c);
            v
                    .getPolicies()
                    .add(c);
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
