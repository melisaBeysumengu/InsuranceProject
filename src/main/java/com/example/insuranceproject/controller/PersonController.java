package com.example.insuranceproject.controller;

import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{tcNumber}")
    public Person getPersonByTcNumber(@PathVariable("tcNumber") Integer tcNumber) {
        return personService.findByTcNo(tcNumber);
    }

    @PostMapping("/")
    public Person createNewPerson(@Valid @RequestBody Person person){
        return personService.createNewPerson(person);
    }

}
