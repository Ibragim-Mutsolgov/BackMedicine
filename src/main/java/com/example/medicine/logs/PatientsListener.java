package com.example.medicine.logs;

import com.example.medicine.domain.Patients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientsListener {

    @JmsListener(destination = "patientsFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN PatientsListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "patientsFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(Patients patients) {
        log.info("IN PatientsListener - findByIdListener: PATIENTS " + patients + " FOUND");
    }

    @JmsListener(destination = "patientsSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(Patients patients) {
        log.info("IN PatientsListener - saveListener: PATIENTS " + patients + " SAVED");
    }

    @JmsListener(destination = "patientsPutSave", containerFactory = "jmsListenerContainerFactory")
    public void putListener(Patients patients) {
        log.info("IN PatientsListener - putListener: PATIENTS " + patients + " UPDATED");
    }

    @JmsListener(destination = "patientsPatchListener", containerFactory = "jmsListenerContainerFactory")
    public void patchListener(Patients patients) {
        log.info("IN PatientsListener - patchListener: PATIENTS " + patients + " UPDATED");
    }

    @JmsListener(destination = "patientsDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(Patients patients) {
        log.info("IN PatientsListener - deleteListener: PATIENTS " + patients + " DELETED");
    }
}
