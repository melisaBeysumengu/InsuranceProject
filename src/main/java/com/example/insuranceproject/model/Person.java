package com.example.insuranceproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ToString
public class Person {

    @Id
    @Column(name = "tc_number", nullable = false)
    private Integer tcNumber;

    @NotBlank(message = "İsim boş bırakılamaz!")
    private String name;

    private String surname;

    private String address;

    private double income;

    private Integer licenceYear;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "owner")
    @JsonManagedReference
    private List<House> houses;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "owner")
    @JsonManagedReference
    private List<Vehicle> vehicles;

}
