package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.CarInsurance;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.CarInsuranceRepository;
import com.example.insuranceproject.repository.OfferRepository;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
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
    public List<Kasko> getAllOffers(String chassisNumber) {
        return vehicleRepository
                .findVehicleByChassisNumber(chassisNumber)
                .getPolicies();
    }

    @Override
    public ResponseEntity<?> addOffer(String chassisNumber, Kasko kasko) {
        Vehicle v = vehicleRepository.findVehicleByChassisNumber(chassisNumber);

        if (v
                .getPolicies()
                .contains(kasko)) {
            return ResponseEntity.ok(new MessageResponse("Bu sigortaya sahip!"));
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
            CarInsurance b = CarInsurance
                    .builder()
                    .kasko(kasko1)
                    .build();
            b.setPrice(kasko.getPrice(), v.getAge(), v.getKilometer());

            offerRepository.save(kasko1);
            /*BaseOffer original = offerRepository.findOfferById(baseOffer.getId());
            CarInsurance c = CarInsurance
                    .builder()
                    .baseOffer(baseOffer)
                    .build();
            c.setPrice(baseOffer.getPrice(), v.getAge(), v.getKilometer());*/
            //offerRepository.save(b.getBaseOffer());
            /*CarInsurance c = (CarInsurance) baseOffer;
            CarInsurance newC = CarInsurance.builder().baseOffer(baseOffer).build();
            newC.setAgeLimit(c.getAgeLimit());
            newC.setKilometerLimit(c.getKilometerLimit());*/

                    /*.builder()
                    .ageLimit(c.getAgeLimit())
                    .kilometerLimit(c.getKilometerLimit())
                    .build();
            newC.setPrice(baseOffer.getPrice(), v.getAge(), v.getKilometer());
            b.setPrice(newC.getPrice());
            b.setContent(newC.getContent());*/
            //c.setPrice(baseOffer.getPrice(), v.getAge(), v.getKilometer());
            //c.setId(offerRepository.count() + 1);
            //offerRepository.save(b);
            v
                    .getPolicies()
                    .add(kasko1);
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
