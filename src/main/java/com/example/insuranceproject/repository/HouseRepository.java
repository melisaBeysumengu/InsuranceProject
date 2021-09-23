package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Kasko;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface HouseRepository extends JpaRepository<House, Long> {

    House findHouseById(Long id);

    @Transactional
    void deleteHouseById(Long id);

}
