package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.PersonRepository;
import com.example.insuranceproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person createNewPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findByTcNo(Integer tcNo) {
        Objects.requireNonNull(tcNo, "TC number cannot be null");
        return personRepository.findPersonByTcNumber(tcNo);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public ResponseEntity<?> addVehicle(Integer tcNumber, Vehicle vehicle) {

        Person p = personRepository.findPersonByTcNumber(tcNumber);
        p.getVehicles().add(vehicle);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("Vehicle saved successfully."));
    }

    @Override
    public ResponseEntity<?> addHouse(Integer tcNumber, House house) {

        Person p = personRepository.findPersonByTcNumber(tcNumber);
        p.getHouses().add(house);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("House saved successfully."));
    }
}
