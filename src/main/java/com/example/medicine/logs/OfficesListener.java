package com.example.medicine.logs;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Offices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OfficesListener {

    @JmsListener(destination = "officesFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN OfficesListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "officesFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(Offices offices) {
        log.info("IN OfficesListener - findByIdListener: OFFICES " + offices + " FOUND");
    }

    @JmsListener(destination = "officesSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(Offices offices) {
        log.info("IN OfficesListener - saveListener: OFFICES " + offices + " SAVED");
    }

    @JmsListener(destination = "officesPutSave", containerFactory = "jmsListenerContainerFactory")
    public void putListener(Offices offices) {
        log.info("IN OfficesListener - putListener: OFFICES " + offices + " UPDATED");
    }

    @JmsListener(destination = "officesPatchListener", containerFactory = "jmsListenerContainerFactory")
    public void patchListener(Offices offices) {
        log.info("IN OfficesListener - patchListener: OFFICES " + offices + " UPDATED");
    }

    @JmsListener(destination = "officesDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(Offices offices) {
        log.info("IN OfficesListener - deleteListener: OFFICES " + offices + " DELETED");
    }
}
