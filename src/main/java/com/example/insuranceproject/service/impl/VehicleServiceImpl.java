package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponseDTO;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.KaskoRepository;
import com.example.insuranceproject.repository.PersonRepository;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;
    KaskoRepository kaskoRepository;
    PersonRepository personRepository;

    @Override
    public Vehicle findVehicleByChassisNumber(String chassisNumber) {
        return vehicleRepository.findVehicleByChassisNumber(chassisNumber);
    }

    @Override
    public void createNewVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public Kasko getAllOffers(String chassisNumber) {
        return vehicleRepository
                .findVehicleByChassisNumber(chassisNumber)
                .getKasko();
    }

    @Override
    public ResponseEntity<?> addOffer(String chassisNumber, Kasko kasko) {
        Vehicle v = vehicleRepository.findVehicleByChassisNumber(chassisNumber);

        if (v
                .getKasko() != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Bu sigortaya sahip!", v.getKasko()));
        } else {
            Kasko original = kaskoRepository.findOfferById(kasko.getId());
            Kasko kasko1 = Kasko
                    .builder()
                    .content(kasko.getContent())
                    .category(kasko.getCategory())
                    .discount(kasko.getDiscount())
                    .price(kasko.getPrice())
                    .provider(kasko.getProvider())
                    .title(kasko.getTitle())
                    .ageLimit(kasko.getAgeLimit())
                    .driverExperienceLimit(kasko.getDriverExperienceLimit())
                    .kilometerLimit(kasko.getKilometerLimit())
                    .vehicle(v)
                    .build();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            kasko1.setCreatedAt(dtf.format(now));

            kaskoRepository.save(kasko1);
            kasko1.setPrice(original.getPrice(), v.getAge(), v.getKilometer());
            v
                    .setKasko(kasko1);
            vehicleRepository.save(v);
            return ResponseEntity.ok(new MessageResponseDTO("Teklif başarıyla kaydedildi.", kasko1));
        }
    }

    @Override
    public ResponseEntity<?> updateVehicle(Vehicle vehicle) {
        Vehicle vehicle1 = vehicleRepository.findVehicleByChassisNumber(vehicle.getChassisNumber());
        Vehicle newVehicle = Vehicle
                .builder()
                .chassisNumber(vehicle.getChassisNumber())
                .plateNumber(
                        vehicle.getPlateNumber() == null ? vehicle1.getPlateNumber() : vehicle.getPlateNumber())
                .age(vehicle.getAge() == null ? vehicle1.getAge() : vehicle.getAge())
                .kasko(vehicle1.getKasko())
                .color(vehicle.getColor() == null ? vehicle1.getColor() : vehicle.getColor())
                .kilometer(vehicle.getKilometer() == null ? vehicle1.getKilometer() : vehicle.getKilometer())
                .owner(vehicle1.getOwner())
                .build();
        vehicleRepository.save(newVehicle);
        return ResponseEntity.ok(new MessageResponseDTO("Araç bilgileri başarıyla düzenlendi."));
    }

    @Override
    public ResponseEntity<?> deleteVehicle(String chassisNumber) {
        vehicleRepository.deleteVehicleByChassisNumber(chassisNumber);
        return ResponseEntity.ok(new MessageResponseDTO("Araç başarıyla silindi."));
    }

    @Override
    public Person findOwnerByChassisNumber(String chassisNumber) {
        return vehicleRepository
                .findVehicleByChassisNumber(chassisNumber)
                .getOwner();
    }
}
