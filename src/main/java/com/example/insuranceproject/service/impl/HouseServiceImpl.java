package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponseDTO;
import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.repository.DaskRepository;
import com.example.insuranceproject.repository.HouseRepository;
import com.example.insuranceproject.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    HouseRepository houseRepository;
    DaskRepository daskRepository;

    @Override
    public House findHouseById(Long id) {
        return houseRepository.findHouseById(id);
    }

    @Override
    public ResponseEntity<?> deleteHouseById(Long id) {
        houseRepository.deleteHouseById(id);
        return ResponseEntity.ok(new MessageResponseDTO("Ev başarıyla silindi."));
    }

    @Override
    public Dask getDask(Long id) {
        return houseRepository
                .findHouseById(id)
                .getDask();
    }

    @Override
    public ResponseEntity<?> createNewHouse(House house) {
        houseRepository.save(house);
        return ResponseEntity.ok(new MessageResponseDTO("Ev başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> addDask(Long id, Dask dask) {
        House house = houseRepository.findHouseById(id);

        if (house
                .getDask() != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Bu sigortaya zaten sahip!", house.getDask()));
        } else {
            Dask original = daskRepository.findDaskById(dask.getId());
            Dask dask1 = Dask
                    .builder()
                    .content(dask.getContent())
                    .category(dask.getCategory())
                    .discount(dask.getDiscount())
                    .price(dask.getPrice())
                    .provider(dask.getProvider())
                    .title(dask.getTitle())
                    .house(house)
                    .houseAgeLimit(dask.getHouseAgeLimit())
                    .build();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            dask1.setCreatedAt(dtf.format(now));

            daskRepository.save(dask1);
            dask1.setPrice(original.getPrice(), house.getBinaInsaTarzi(), house.getIsInDangerZone(), house.getValue());
            house
                    .setDask(dask1);
            houseRepository.save(house);
            return ResponseEntity.ok(new MessageResponseDTO("Teklif başarıyla kaydedildi.", dask1));
        }
    }

    @Override
    public ResponseEntity<?> updateHouse(House house) {
        House house1 = houseRepository.findHouseById(house.getId());
        House newHouse = House
                .builder()
                .owner(house1.getOwner())
                .address(house.getAddress() == null ? house1.getAddress() : house.getAddress())
                .id(house.getId())
                .area(house.getArea() == null ? house1.getArea() : house.getArea())
                .bedrooms(house.getBedrooms() == null ? house1.getBedrooms() : house.getBedrooms())
                .binaInsaTarzi(house.getBinaInsaTarzi() == null ? house1.getBinaInsaTarzi() : house.getBinaInsaTarzi())
                .isInDangerZone(
                        house.getIsInDangerZone() == null ? house1.getIsInDangerZone() : house.getIsInDangerZone())
                .dask(house1.getDask())
                .value(house.getValue() == null ? house1.getValue() : house.getValue())
                .build();
        houseRepository.save(newHouse);
        return ResponseEntity.ok(new MessageResponseDTO("Ev bilgileri başarıyla düzenlendi."));
    }

    @Override
    public ResponseEntity<?> deleteHouse(Long id) {
        houseRepository.deleteHouseById(id);
        return ResponseEntity.ok(new MessageResponseDTO("Ev başarıyla silindi."));
    }

    @Override
    public Person findOwnerById(Long id) {
        return houseRepository
                .findHouseById(id)
                .getOwner();
    }
}
