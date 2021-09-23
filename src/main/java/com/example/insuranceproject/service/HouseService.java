package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import org.springframework.http.ResponseEntity;

public interface HouseService {

    House findHouseById(Long id);

    ResponseEntity<?> deleteHouseById(Long id);

    Dask getDask(Long id);

    ResponseEntity<?> createNewHouse(House house);

    ResponseEntity<?> addDask(Long id, Dask dask);

    ResponseEntity<?> updateHouse(House house);

    ResponseEntity<?> deleteHouse(Long id);

    Person findOwnerById(Long id);

}
