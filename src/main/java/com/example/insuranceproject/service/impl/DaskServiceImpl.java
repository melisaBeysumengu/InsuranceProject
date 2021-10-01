package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.AllOffersResponseDTO;
import com.example.insuranceproject.model.Dask;
import com.example.insuranceproject.repository.DaskRepository;
import com.example.insuranceproject.service.DaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DaskServiceImpl implements DaskService {

    private final DaskRepository daskRepository;

    @Override
    public List<Dask> getAll() {
        List<Dask> daskList = daskRepository.findAll();
        List<Dask> newList = new ArrayList<>();
        for (Dask dask :
                daskList) {
            if (dask
                    .getContent()
                    .equals("Orijinal ücret.")) {
                newList.add(dask);
            }
        }
        return newList;
    }

    @Override
    public Dask getDaskById(Long id) {
        return daskRepository.findDaskById(id);
    }

    @Override
    public Dask createNewDask(Dask dask) {
        return daskRepository.save(dask);
    }

    @Override
    public Dask getOriginalDask() {
        return daskRepository.findDaskByContent("Orijinal ücret.");
    }

    @Override
    public List<AllOffersResponseDTO> gelAllDasks() {
        List<Dask> daskList = daskRepository.findDasksByContentNot("Orijinal ücret.");
        List<AllOffersResponseDTO> responseDTOS = new ArrayList<>();
        for (Dask dask :
                daskList) {
            responseDTOS.add(new AllOffersResponseDTO(dask
                    .getHouse()
                    .getOwner(), dask.getHouse(), dask));
        }
        return responseDTOS;
    }
}
