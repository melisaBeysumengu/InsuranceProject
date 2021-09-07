package com.example.insuranceproject.model;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CarInsurance extends BaseOffer {

    private Integer ageLimit;

    private Integer kilometerLimit;

    public void setPrice(double price, int age, int kilometer) {
        if(age < ageLimit){
            setPrice(price*((double) (100-getDiscount())/100));
        }
        if(kilometer < kilometerLimit){
            setPrice(getPrice()*((double) (100-getDiscount())/100));
        }
    }

}
