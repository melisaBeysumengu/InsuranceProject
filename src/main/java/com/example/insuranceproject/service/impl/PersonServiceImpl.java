package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.repository.PersonRepository;
import com.example.insuranceproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
