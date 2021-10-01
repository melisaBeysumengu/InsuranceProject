package com.example.insuranceproject.service;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.model.Person;

import java.util.List;

public interface DaskService {

    List<Dask> getAll();

    Dask getDaskById(Long id);

    Dask createNewDask(Dask dask);

    Dask getOriginalDask();

    List<AllOffersResponseDTO> gelAllDasks();
}
