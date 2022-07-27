package com.example.medicine.logs;

import com.example.medicine.domain.Patients;
import com.example.medicine.domain.Work;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkListener {

    @JmsListener(destination = "workFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN WorkListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "workFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(Work work) {
        log.info("IN WorkListener - findByIdListener: WORK " + work + " FOUND");
    }

    @JmsListener(destination = "workSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(Work work) {
        log.info("IN WorkListener - saveListener: WORK " + work + " SAVED");
    }

    @JmsListener(destination = "workPutSave", containerFactory = "jmsListenerContainerFactory")
    public void putListener(Work work) {
        log.info("IN WorkListener - putListener: WORK " + work + " UPDATED");
    }

    @JmsListener(destination = "workPatchListener", containerFactory = "jmsListenerContainerFactory")
    public void patchListener(Work work) {
        log.info("IN WorkListener - patchListener: WORK " + work + " UPDATED");
    }

    @JmsListener(destination = "workDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(Work work) {
        log.info("IN WorkListener - deleteListener: WORK " + work + " DELETED");
    }
}
