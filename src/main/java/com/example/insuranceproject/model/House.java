package com.example.insuranceproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bedrooms;

    private Double area;

    private String address;

    private Double value;

    @OneToMany
    private List<BaseOffer> policies = new ArrayList<>();

}
