package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.BaseOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<BaseOffer, Long> {

    List<BaseOffer> findAllByCategory(String category);

    BaseOffer findOfferById(Long id);

    @Override
    List<BaseOffer> findAll();

    BaseOffer save(BaseOffer baseOffer);
}
