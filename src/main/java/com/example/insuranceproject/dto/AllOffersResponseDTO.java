package com.example.insuranceproject.dto;

import com.example.insuranceproject.model.*;
import lombok.*;

@Getter

@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
public class AllOffersResponseDTO {
    private Person person;

    private House house;
    private Dask dask;

    private Vehicle vehicle;
    private Kasko kasko;

    public AllOffersResponseDTO(Person person, House house, Dask dask) {
        this.person = person;
        this.house = house;
        this.dask = dask;
    }

    public AllOffersResponseDTO(Person person, Vehicle vehicle, Kasko kasko) {
        this.person = person;
        this.vehicle = vehicle;
        this.kasko = kasko;
    }
}
