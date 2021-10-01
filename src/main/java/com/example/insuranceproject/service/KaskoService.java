package com.example.insuranceproject.service;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.Kasko;

import java.util.List;

public interface KaskoService {

    Kasko getKaskoByCategory();

    List<Kasko> getAll();

    Kasko getKaskoById(Long id);

    Kasko createNewKasko(Kasko kasko);

    List<AllOffersResponseDTO> gelAllKaskos();
}
