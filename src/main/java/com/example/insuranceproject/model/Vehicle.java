package com.example.insuranceproject.model;

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
    @Column(length = 17)
    private String chassisNumber;

    @Size(max = 7)
    private String plateNumber;

    private String color;

    private Integer age;

    private Integer kilometer;

    @OneToMany
    private List<Kasko> policies = new ArrayList<>();


}
