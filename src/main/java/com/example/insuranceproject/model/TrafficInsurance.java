package com.example.insuranceproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TrafficInsurance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kasko kasko;

    public void setPrice(double price, Integer licenceYear) {
        if(kasko.getDriverExperienceLimit()<licenceYear){
            kasko.setPrice(price*((double) (100- kasko.getDiscount())/100));
            kasko.setContent("Sürüş deneyiminiz fazla olduğu için orijinal fiyat (" + price + ") üzerinden %" + kasko.getDiscount() + " indirim uygulandı.");
        }
    }
}
