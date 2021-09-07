package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.BaseOffer;
import com.example.insuranceproject.model.CarInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {

    @Override
    <S extends CarInsurance> S save(S s);
}
