package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.model.CarInsurance;
import com.example.insuranceproject.repository.OfferRepository;
import com.example.insuranceproject.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public List<BaseOffer> getOffersByCategory(String category, Integer age, Integer kilometer) {
        List<BaseOffer> baseOfferList = offerRepository.findAllByCategory(category);
        for (BaseOffer baseOffer :
                baseOfferList) {
            ((CarInsurance) baseOffer).setPrice(baseOffer.getPrice(), age, kilometer);
        }
        return baseOfferList;
    }

    @Override
    public List<BaseOffer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public BaseOffer getOfferById(Long id) {
        return offerRepository.findOfferById(id);
    }

    @Override
    public BaseOffer createNewOffer(BaseOffer baseOffer) {
        Objects.requireNonNull(baseOffer.getTitle(), "Title cannot be null");
        return offerRepository.save(baseOffer);
    }
}
