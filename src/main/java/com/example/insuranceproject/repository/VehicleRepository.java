package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Offer;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    void deleteVehicleByChassisNumber(String chassisNumber);

}
