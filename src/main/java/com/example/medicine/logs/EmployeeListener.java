package com.example.medicine.logs;

import com.example.medicine.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeListener {

    @JmsListener(destination = "employeeFindAll", containerFactory = "jmsListenerContainerFactory")
    public void findAllListener(boolean b) {
        if(b) log.info("IN EmployeeListener - findAllListener: REQUEST PROCESSED");
    }

    @JmsListener(destination = "employeeFindById", containerFactory = "jmsListenerContainerFactory")
    public void findByIdListener(Employee employee) {
        log.info("IN EmployeeListener - findByIdListener: EMPLOYEE " + employee + " FOUND");
    }

    @JmsListener(destination = "employeeSave", containerFactory = "jmsListenerContainerFactory")
    public void saveListener(Employee employee) {
        log.info("IN EmployeeListener - saveListener: EMPLOYEE " + employee + " SAVED");
    }

    @JmsListener(destination = "employeePutSave", containerFactory = "jmsListenerContainerFactory")
    public void putListener(Employee employee) {
        log.info("IN EmployeeListener - putListener: EMPLOYEE " + employee + " UPDATED");
    }

    @JmsListener(destination = "employeePatchListener", containerFactory = "jmsListenerContainerFactory")
    public void patchListener(Employee employee) {
        log.info("IN EmployeeListener - patchListener: EMPLOYEE " + employee + " UPDATED");
    }

    @JmsListener(destination = "employeeDelete", containerFactory = "jmsListenerContainerFactory")
    public void deleteListener(Employee employee) {
        log.info("IN EmployeeListener - deleteListener: EMPLOYEE " + employee + " DELETED");
    }
}
