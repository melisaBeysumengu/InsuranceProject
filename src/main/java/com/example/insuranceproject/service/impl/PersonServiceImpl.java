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
    public ResponseEntity<?> createNewPerson(Person person) {
        personRepository.save(person);
        return ResponseEntity.ok(new MessageResponse("Kullanıcı başarıyla oluşturuldu."));
    }

    @Override
    public Person findByTcNo(Integer tcNo) {
        Objects.requireNonNull(tcNo, "TC kimlik numarası boş olamaz!");
        return personRepository.findPersonByTcNumber(tcNo);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public ResponseEntity<?> addVehicle(Integer tcNumber, Vehicle vehicle) {

        Person p = personRepository.findPersonByTcNumber(tcNumber);
        p
                .getVehicles()
                .add(vehicle);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("Araç başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> addHouse(Integer tcNumber, House house) {

        Person p = personRepository.findPersonByTcNumber(tcNumber);
        p
                .getHouses()
                .add(house);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("Ev başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> updatePerson(Person person) {
        Person p = personRepository.findPersonByTcNumber(person.getTcNumber());
        Person newPerson = Person
                .builder()
                .tcNumber(p.getTcNumber())
                .name(person.getName()!=null ? person.getName() : p.getName())
                .surname(person.getSurname()!=null ? person.getSurname() : p.getSurname())
                .address(person.getAddress()!=null ? person.getAddress() : p.getAddress())
                .income(person.getIncome())
                .build();
        personRepository.save(newPerson);
        return ResponseEntity.ok(new MessageResponse("Kullanıcı bilgileri başarıyla düzenlendi."));
    }
}
