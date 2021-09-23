package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.repository.OfferRepository;
import com.example.insuranceproject.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public Kasko getOffersByCategory(String category, Integer age, Integer kilometer) {
        Kasko kasko = offerRepository.findKaskoByContent("Orijinal ücret.");
        System.out.println(offerRepository.findKaskoByContent("Orijinal ücret."));
        Kasko newKasko = Kasko
                .builder()
                .category(kasko.getCategory())
                .ageLimit(kasko.getAgeLimit())
                .content(kasko.getContent())
                .discount(kasko.getDiscount())
                .driverExperienceLimit(kasko.getDriverExperienceLimit())
                .price(kasko.getPrice())
                .provider(kasko.getProvider())
                .title(kasko.getTitle())
                .kilometerLimit(kasko.getKilometerLimit())
                .build();
        newKasko.setPrice(kasko.getPrice(), age, kilometer);
        return kasko;
    }

    @Override
    public List<Kasko> getAll() {
        List<Kasko> kaskoList = offerRepository.findAll();
        List<Kasko> newList = new ArrayList<>();
        for (Kasko kasko :
                kaskoList) {
            if (kasko
                    .getContent()
                    .equals("Orijinal ücret.")) {
                newList.add(kasko);
            }
        }
        return newList;
    }

    @Override
    public Kasko getOfferById(Long id) {
        return offerRepository.findOfferById(id);
    }

    @Override
    public Kasko createNewOffer(Kasko kasko) {
        Objects.requireNonNull(kasko.getTitle(), "Title cannot be null");
        return offerRepository.save(kasko);
    }
}
