package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findPersonByTcNumber(Integer tcNumber);

    @Transactional
    void deleteByTcNumber(Integer tcNumber);

    @Override
    List<Person> findAll();

    boolean existsByTcNumber(Integer tcNumber);

    Person findByVehiclesChassisNumber(String chassisnumber);

    Person findByVehicles(Vehicle vehicle);
}
