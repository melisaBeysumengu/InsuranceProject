package com.example.insuranceproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    @Column(name = "tc_number", nullable = false)
    private Integer tcNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String address;

    @NotNull
    private double income;

    @OneToMany
    private List<House> houses;

    @OneToMany
    private List<Vehicle> vehicles;

}
