package com.example.insuranceproject.repository;

import com.example.insuranceproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findPersonByTcNumber(Integer tcNumber);

    @Override
    List<Person> findAll();

}
