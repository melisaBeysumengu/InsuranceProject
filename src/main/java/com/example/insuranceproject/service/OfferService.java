package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Offer;

import java.util.List;

public interface OfferService {

    List<Offer> getOffersByCategory(String category);

    List<Offer> getAll();

    Offer getOfferById(Long id);

    Offer createNewOffer(Offer offer);

}
