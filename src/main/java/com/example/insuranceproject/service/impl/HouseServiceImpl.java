package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.service.HouseService;
import org.springframework.http.ResponseEntity;

public class HouseServiceImpl implements HouseService {
    @Override
    public House findHouseById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteHouseById(Long id) {
        return null;
    }

    @Override
    public Dask getDask(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createNewHouse(House house) {
        return null;
    }

    @Override
    public ResponseEntity<?> addDask(Long id, Dask dask) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateHouse(House house) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteHouse(Long id) {
        return null;
    }

    @Override
    public Person findOwnerById(Long id) {
        return null;
    }
}
