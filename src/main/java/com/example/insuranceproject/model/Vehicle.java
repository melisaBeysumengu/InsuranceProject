package com.example.insuranceproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vehicle {
    @Id
    @NotBlank
    private String chassisNumber;

    private String plateNumber;

    private String color;

    private Integer age;

    private Integer kilometer;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "vehicle")
    @JsonManagedReference
    private Kasko kasko;

    @ManyToOne
    @JoinColumn(name="owner_tc_number")
    @JsonBackReference
    private Person owner;


}
