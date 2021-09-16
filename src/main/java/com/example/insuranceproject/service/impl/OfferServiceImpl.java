package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.CarInsurance;
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
    public List<Kasko> getOffersByCategory(String category, Integer age, Integer kilometer) {
        List<Kasko> kaskoList = offerRepository.findAllByCategory(category);
        List<Kasko> newList = new ArrayList<>();
        for (Kasko kasko :
                kaskoList) {
            if(kasko
                    .getContent().equals("Orijinal ücret.")){
                CarInsurance c = CarInsurance.builder().kasko(kasko).build();
                c.setPrice(kasko.getPrice(), age, kilometer);
                newList.add(c.getKasko());
            }
        }
        return newList;
    }

    @Override
    public List<Kasko> getAll() {
        List<Kasko> kaskoList = offerRepository.findAll();
        List<Kasko> newList = new ArrayList<>();
        for (Kasko kasko :
                kaskoList) {
            if(kasko
                    .getContent().equals("Orijinal ücret.")){
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
