package com.example.insuranceproject.service;

import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    Person createNewPerson(Person person);

    Person findByTcNo(Integer tcNo);

    List<Person> getAll();

    ResponseEntity<?> addVehicle(Integer tcNumber, Vehicle vehicle);

    ResponseEntity<?> addHouse(Integer tcNumber, House house);

}
