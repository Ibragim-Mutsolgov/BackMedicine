package com.example.medicine.logs;

import com.example.medicine.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserListener {

    @JmsListener(destination = "userFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN UserListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "userFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(User user) {
        log.info("IN UserListener - findByIdListener: USER " + user + " FOUND");
    }

    @JmsListener(destination = "userSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(User user) {
        log.info("IN UserListener - saveListener: USER " + user + " SAVED");
    }

    @JmsListener(destination = "userDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(User user) {
        log.info("IN UserListener - deleteListener: USER " + user + " DELETED");
    }

    @JmsListener(destination = "userNotFound", containerFactory = "jmsListenerContainerFactory")
    public void notFound(String user) {
        log.info("IN UserListener: USER " + user + " NOT FOUND IN DATABASE");
    }

    @JmsListener(destination = "tokenIsInvalid", containerFactory = "jmsListenerContainerFactory")
    public void isInvalid(String message) {
        log.info("IN UserListener: TOKEN IS INVALID: " + message);
    }
}
