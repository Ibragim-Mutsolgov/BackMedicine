package com.example.medicine.initial;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Patients;
import com.example.medicine.domain.People;
import com.example.medicine.serviceimpl.service.PeopleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
public class PeopleInitializing implements CommandLineRunner {

    private PeopleService peopleService;

    @Override
    public void run(String... args) throws Exception {
        if(peopleService.findAll().size() == 0){
            peopleService.save(
                    new People(
                            "Ivanov",
                            "Ivan",
                            "Ivanovich",
                            LocalDate.now(),
                            1,
                            "Московская область, Химки",
                            2563L,
                            565656L,
                            "Отдел УФМС",
                            LocalDate.now(),
                            "202-111",
                            "Москва",
                            "Лефортово",
                            "Авиамоторная",
                            "Авиамоторная 8а",
                            new Patients(
                                    "545554",
                                    "545665465",
                                    "56465646"
                            ),
                            new Employee(
                                    "Врач"
                            )
                    )
            );
            log.info("IN PeopleInitializing: PEOPLE IS CREATED");
        }
    }
}
