package com.example.insuranceproject.service.impl;

import com.example.insuranceproject.dto.MessageResponseDTO;
import com.example.insuranceproject.exception.MyNotFoundException;
import com.example.insuranceproject.model.House;
import com.example.insuranceproject.model.Person;
import com.example.insuranceproject.model.Vehicle;
import com.example.insuranceproject.repository.PersonRepository;
import com.example.insuranceproject.repository.VehicleRepository;
import com.example.insuranceproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return ResponseEntity.ok(new MessageResponseDTO("Kullanıcı başarıyla oluşturuldu."));
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
        Person p = personRepository.findPersonByTcNumber(tcNumber).orElseThrow(MyNotFoundException::new);



        vehicle.setOwner(p);
        vehicleRepository.save(vehicle);
        p
                .getVehicles()
                .add(vehicle);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponseDTO("Araç başarıyla kaydedildi."));
    }

    @Override
    public ResponseEntity<?> addHouse(Integer tcNumber, House house) {
        Person p = personRepository.findPersonByTcNumber(tcNumber).orElseThrow(MyNotFoundException::new);

        if(house.getValue()>100000 && house.getArea()<100){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("ev fiyatı ve metrekare uyuşmuyor"));
        }

        house.setOwner(p);
        p
                .getHouses()
                .add(house);
        personRepository.save(p);

        return ResponseEntity.ok(new MessageResponseDTO("Ev başarıyla kaydedildi."));
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
        return ResponseEntity.ok(new MessageResponseDTO("Kullanıcı bilgileri başarıyla düzenlendi."));
    }

    @Override
    public ResponseEntity<?> deletePerson(Integer tcNumber) {
        if(personRepository.existsByTcNumber(tcNumber)){
            personRepository.deleteByTcNumber(tcNumber);
            return ResponseEntity.ok(new MessageResponseDTO("Kullanıcı başarıyla silindi."));
        }else return ResponseEntity.ok(new MessageResponseDTO("Kullanıcı sistemde bulunamadı."));
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
