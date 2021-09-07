package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleByChassisNumber(String chassisNumber);

    @Transactional
    void deleteVehicleByChassisNumber(String chassisNumber);

}
