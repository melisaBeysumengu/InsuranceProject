package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Kasko;

import java.util.List;

public interface OfferService {

    List<Kasko> getOffersByCategory(String category, Integer age, Integer kilometer);

    List<Kasko> getAll();

    Kasko getOfferById(Long id);

    Kasko createNewOffer(Kasko kasko);

}
