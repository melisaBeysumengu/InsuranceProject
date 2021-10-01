package com.example.insuranceproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "boş olamaz")
    private Double value;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "house")
    @JsonManagedReference
    private Dask dask;

    private Boolean isInDangerZone;

    @ManyToOne
    @JoinColumn(name="owner_tc_number")
    @JsonBackReference
    private Person owner;

    private String  binaInsaTarzi;

}
