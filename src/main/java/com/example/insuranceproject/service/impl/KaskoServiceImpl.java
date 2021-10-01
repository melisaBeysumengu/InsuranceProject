package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.model.Kasko;
import com.example.insuranceproject.repository.KaskoRepository;
import com.example.insuranceproject.service.KaskoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class KaskoServiceImpl implements KaskoService {

    private final KaskoRepository kaskoRepository;

    @Override
    public Kasko getKaskoByCategory() {
        return kaskoRepository.findKaskoByContent("Orijinal ücret.");
    }

    @Override
    public List<Kasko> getAll() {
        List<Kasko> kaskoList = kaskoRepository.findAll();
        List<Kasko> newList = new ArrayList<>();
        for (Kasko kasko :
                kaskoList) {
            if (kasko
                    .getContent()
                    .equals("Orijinal ücret.")) {
                newList.add(kasko);
            }
        }
        return newList;
    }

    @Override
    public Kasko getKaskoById(Long id) {
        return kaskoRepository.findOfferById(id);
    }

    @Override
    public Kasko createNewKasko(Kasko kasko) {
        Objects.requireNonNull(kasko.getTitle(), "Title cannot be null");
        return kaskoRepository.save(kasko);
    }

    @Override
    public List<AllOffersResponseDTO> gelAllKaskos() {
        List<Kasko> kaskoList = kaskoRepository.findKaskosByContentNot("Orijinal ücret.");
        List<AllOffersResponseDTO> responseDTOS = new ArrayList<>();
        for (Kasko kasko :
                kaskoList) {
            responseDTOS.add(new AllOffersResponseDTO(kasko
                    .getVehicle()
                    .getOwner(), kasko.getVehicle(), kasko));
        }
        return responseDTOS;
    }
}
