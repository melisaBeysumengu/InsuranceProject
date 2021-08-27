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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @OneToMany
    private List<Offer> policies;

    @NotBlank
    @Column(length = 17)
    private String chassisNumber;

}
