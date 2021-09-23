package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponse;
import com.example.insuranceproject.exception.MyNotFoundException;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.PersonRepository;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final VehicleRepository vehicleRepository;


    @Override
    public ResponseEntity<?> createNewPerson(Person person) {
        personRepository.save(person);
        return ResponseEntity.ok(new MessageResponse("Kullanıcı başarıyla oluşturuldu."));
    }

    @Override
    public Person findByTcNo(Integer tcNo) {
        Objects.requireNonNull(tcNo, "TC kimlik numarası boş olamaz!");
        return personRepository.findPersonByTcNumber(tcNo).orElseThrow(MyNotFoundException::new);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public ResponseEntity<?> addVehicle(Integer tcNumber, Vehicle vehicle) {

        Person p = personRepository.findPersonByTcNumber(tcNumber).get();
        vehicle.setOwner(p);
        vehicleRepository.save(vehicle);
        p
                .getVehicles()
                .add(vehicle);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("Araç başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> addHouse(Integer tcNumber, House house) {

        Person p = personRepository.findPersonByTcNumber(tcNumber).get();
        p
                .getHouses()
                .add(house);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponse("Ev başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> updatePerson(Person person) {
        Person p = personRepository.findPersonByTcNumber(person.getTcNumber()).get();
        Person newPerson = Person
                .builder()
                .tcNumber(p.getTcNumber())
                .name(person.getName()!=null ? person.getName() : p.getName())
                .surname(person.getSurname()!=null ? person.getSurname() : p.getSurname())
                .address(person.getAddress()!=null ? person.getAddress() : p.getAddress())
                .licenceYear(person.getLicenceYear()!=null ? person.getLicenceYear() : p.getLicenceYear())
                .income(person.getIncome())
                .vehicles(p.getVehicles())
                .houses(p.getHouses())
                .build();
        personRepository.save(newPerson);
        return ResponseEntity.ok(new MessageResponse("Kullanıcı bilgileri başarıyla düzenlendi."));
    }

    @Override
    public ResponseEntity<?> deletePerson(Integer tcNumber) {
        if(personRepository.existsByTcNumber(tcNumber)){
            personRepository.deleteByTcNumber(tcNumber);
            return ResponseEntity.ok(new MessageResponse("Kullanıcı başarıyla silindi."));
        }else return ResponseEntity.ok(new MessageResponse("Kullanıcı sistemde bulunamadı."));
    }

    @Override
    public Person findVehicleByChassisNumber(String chassisNumber) {
        Person p = new Person();
        for (Person person :
                personRepository.findAll()) {
            for (Vehicle vehicle :
                    person.getVehicles()) {
                if(vehicle.getChassisNumber().equals(chassisNumber)){
                    p = person;
                }
            }
        }
        if(p.getTcNumber()==null){
            throw new MyNotFoundException();
        }else return p;
    }
}
