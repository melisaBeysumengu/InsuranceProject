package com.example.insuranceproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne
    private Dask dask;

    @ManyToOne
    @JoinColumn(name="owner_tc_number")
    @JsonBackReference
    private Person owner;

}
