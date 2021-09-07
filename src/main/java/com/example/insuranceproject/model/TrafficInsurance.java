package com.example.insuranceproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TrafficInsurance extends  BaseOffer{
    private Integer driverExperienceLimit;

    public void setPrice(double price, Integer licenceYear) {
        if(driverExperienceLimit<licenceYear){
            setPrice(price);
        }
    }
}
