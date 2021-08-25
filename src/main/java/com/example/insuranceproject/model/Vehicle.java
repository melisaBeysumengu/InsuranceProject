package com.example.insuranceproject.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 7)
    private String plateNumber;

    @NotBlank
    private String color;

    @NotBlank
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @NotBlank
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @NotBlank
    @OneToMany
    private List<Offer> policies;

}
