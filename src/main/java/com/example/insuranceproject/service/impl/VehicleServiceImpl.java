package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.OfferRepository;
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
    OfferRepository offerRepository;
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
            return ResponseEntity.ok(new MessageResponse("Bu sigortaya sahip!", v.getKasko()));
        } else {
            System.out.println(kasko.getPrice());
            Kasko original = offerRepository.findOfferById(kasko.getId());
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
                    .build();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            kasko1.setCreatedAt(dtf.format(now));
            offerRepository.save(kasko1);
            kasko1.setPrice(original.getPrice(), v.getAge(), v.getKilometer());
            v
                    .setKasko(kasko1);
            vehicleRepository.save(v);
            return ResponseEntity.ok(new MessageResponse("Teklif başarıyla kaydedildi.", kasko1));
        }
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

    @Override
    public Person findOwnerByChassisNumber(String chassisNumber) {
        return vehicleRepository
                .findVehicleByChassisNumber(chassisNumber)
                .getOwner();
    }
}
