package com.example.insuranceproject.service;

import com.example.insuranceproject.model.Person;

import java.util.List;

public interface PersonService {

    Person createNewPerson(Person person);

    Person findByTcNo(Integer tcNo);

    List<Person> getAll();

}
