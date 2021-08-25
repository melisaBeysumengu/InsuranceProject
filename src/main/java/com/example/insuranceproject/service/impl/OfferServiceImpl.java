package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Offer;
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
    public List<Offer> getOffersByCategory(String category) {
        return offerRepository.findAllByCategory(category);
    }

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findOfferById(id);
    }

    @Override
    public Offer createNewOffer(Offer offer) {
        Objects.requireNonNull(offer.getTitle(), "Title cannot be null");
        return offerRepository.save(offer);
    }
}
