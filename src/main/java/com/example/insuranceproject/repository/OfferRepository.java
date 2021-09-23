package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Kasko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Kasko, Long> {

    Kasko findKaskoByContent(String content);

    Kasko findOfferById(Long id);

    @Override
    List<Kasko> findAll();

    Kasko save(Kasko kasko);
}
