package com.example.medicine.initial;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Patients;
import com.example.medicine.model.People;
import com.example.medicine.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class PeopleInitializing implements CommandLineRunner {

    private PeopleRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if(repository.findAll().size() == 0){
            repository.save(
                    new People(
                            "Ivanov",
                            "Ivan",
                            "Ivanovich",
                            new Date(),
                            1,
                            "Московская область, Химки",
                            2563L,
                            565656L,
                            "Отдел УФМС",
                            new Date(),
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
