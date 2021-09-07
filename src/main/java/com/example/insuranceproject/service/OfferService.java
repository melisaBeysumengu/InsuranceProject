package com.example.insuranceproject.service;

import com.example.insuranceproject.model.BaseOffer;

import java.util.List;

public interface OfferService {

    List<BaseOffer> getOffersByCategory(String category, Integer age, Integer kilometer);

    List<BaseOffer> getAll();

    BaseOffer getOfferById(Long id);

    BaseOffer createNewOffer(BaseOffer baseOffer);

}
