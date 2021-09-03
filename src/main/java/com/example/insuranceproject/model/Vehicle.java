package com.example.insuranceproject.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
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

    @OneToMany
    private List<Offer> policies = new ArrayList<>();


}
