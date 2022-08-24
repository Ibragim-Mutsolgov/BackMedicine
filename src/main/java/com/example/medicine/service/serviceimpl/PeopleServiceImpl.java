package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Patients;
import com.example.medicine.model.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private PeopleRepository peopleRepository;

    @Override
    public ResponseEntity<List<People>> findAll() {
        return ResponseEntity.ok().body(peopleRepository.findAll());
    }

    @Override
    public ResponseEntity<People> findById(Long id) {
        return peopleRepository.findById(id)
                .map(people -> ResponseEntity.ok().body(people))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<People> save(People people) {
        return ResponseEntity.ok().body(peopleRepository.save(people));
    }

    @Override
    public ResponseEntity<People> putSave(Long id, People people) {
        return peopleRepository.findById(id)
                .map(peopleSave -> {
                    peopleSave.setId(id);
                    peopleSave.setSurname(people.getSurname());
                    peopleSave.setName(people.getName());
                    peopleSave.setPatronymic(people.getPatronymic());
                    peopleSave.setDate(people.getDate());
                    peopleSave.setGender(people.getGender());
                    peopleSave.setPlaceBirth(people.getPlaceBirth());
                    peopleSave.setPassportSeries(people.getPassportSeries());
                    peopleSave.setPassportNumber(people.getPassportNumber());
                    peopleSave.setPassportIssue(people.getPassportIssue());
                    peopleSave.setDateIssue(people.getDateIssue());
                    peopleSave.setDepartmentCode(people.getDepartmentCode());
                    peopleSave.setRegion(people.getRegion());
                    peopleSave.setStation(people.getStation());
                    peopleSave.setLocality(people.getLocality());
                    peopleSave.setStreet(people.getStreet());
                    peopleSave.setPatients(people.getPatients());
                    peopleSave.setEmployee(people.getEmployee());
                    return ResponseEntity.ok().body(peopleRepository.save(peopleSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<People> patchSave(Long id, People people) {
        return peopleRepository.findById(id)
                .map(peopleSave -> {
                    if(people.getSurname() != null){
                        peopleSave.setSurname(people.getSurname());
                    }
                    if(people.getName() != null){
                        peopleSave.setName(people.getName());
                    }
                    if(people.getPatronymic() != null){
                        peopleSave.setPatronymic(people.getPatronymic());
                    }
                    if(people.getDate() != null){
                        peopleSave.setDate(people.getDate());
                    }
                    if(people.getGender() != 0){
                        peopleSave.setGender(people.getGender());
                    }
                    if(people.getPlaceBirth() != null){
                        peopleSave.setPlaceBirth(people.getPlaceBirth());
                    }
                    if(people.getPassportSeries() != null){
                        peopleSave.setPassportSeries(people.getPassportSeries());
                    }
                    if(people.getPassportNumber() != null){
                        peopleSave.setPassportNumber(people.getPassportNumber());
                    }
                    if(people.getPassportIssue() != null){
                        peopleSave.setPassportIssue(people.getPassportIssue());
                    }
                    if(people.getDateIssue() != null){
                        peopleSave.setDateIssue(people.getDateIssue());
                    }
                    if(people.getDepartmentCode() != null){
                        peopleSave.setDepartmentCode(people.getDepartmentCode());
                    }
                    if(people.getRegion() != null){
                        peopleSave.setRegion(people.getRegion());
                    }
                    if(people.getStation() != null){
                        peopleSave.setStation(people.getStation());
                    }
                    if(people.getLocality() != null){
                        peopleSave.setLocality(people.getLocality());
                    }
                    if(people.getStreet() != null){
                        peopleSave.setStreet(people.getStreet());
                    }
                    if(people.getEmployee() != null) {
                        Employee employee = new Employee();
                        if (people.getEmployee().getEmployee_name() != null) {
                            employee.setEmployee_name(people.getEmployee().getEmployee_name());
                            peopleSave.setEmployee(employee);
                        }
                    }
                    if(people.getPatients() != null) {
                        Patients patients = new Patients();
                        if (people.getPatients().getPatients_number_ambulant_card() != null) {
                            patients.setPatients_number_ambulant_card(people.getPatients().getPatients_number_ambulant_card());
                        }
                        if (people.getPatients().getPatients_policy() != null) {
                            patients.setPatients_policy(people.getPatients().getPatients_policy());
                        }
                        if (people.getPatients().getPatients_type_policy() != null) {
                            patients.setPatients_type_policy(people.getPatients().getPatients_type_policy());
                        }
                        peopleSave.setPatients(patients);
                    }
                    return ResponseEntity.ok().body(peopleRepository.save(peopleSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<People> delete(Long id) {
        return peopleRepository.findById(id)
                .map(people -> {
                    peopleRepository.deleteById(people.getId());
                    return ResponseEntity.ok().body(people);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}