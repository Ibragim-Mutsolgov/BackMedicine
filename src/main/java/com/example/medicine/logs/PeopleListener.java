package com.example.medicine.logs;

import com.example.medicine.domain.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PeopleListener {

    @JmsListener(destination = "peopleFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN PeopleListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "peopleFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(People people) {
        log.info("IN PeopleListener - findByIdListener: PEOPLE " + people + " FOUND");
    }

    @JmsListener(destination = "peopleSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(People people) {
        log.info("IN PeopleListener - saveListener: PEOPLE " + people + " SAVED");
    }

    @JmsListener(destination = "peoplePutSave", containerFactory = "jmsListenerContainerFactory")
    public void putListener(People people) {
        log.info("IN PeopleListener - putListener: PEOPLE " + people + " UPDATED");
    }

    @JmsListener(destination = "peoplePatchListener", containerFactory = "jmsListenerContainerFactory")
    public void patchListener(People people) {
        log.info("IN PeopleListener - patchListener: PEOPLE " + people + " UPDATED");
    }

    @JmsListener(destination = "peopleDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(People people) {
        log.info("IN PeopleListener - deleteListener: PEOPLE " + people + " DELETED");
    }
}
