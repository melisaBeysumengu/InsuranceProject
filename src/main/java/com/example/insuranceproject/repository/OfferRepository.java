package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByCategory(String category);

    Offer findOfferById(Long id);

    @Override
    List<Offer> findAll();

    Offer save(Offer offer);
}
