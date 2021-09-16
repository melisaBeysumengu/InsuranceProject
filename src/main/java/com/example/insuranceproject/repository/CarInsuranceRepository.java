package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.CarInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {

    @Override
    <S extends CarInsurance> S save(S s);

    void save(Kasko b);
}
