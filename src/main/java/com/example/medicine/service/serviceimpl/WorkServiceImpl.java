package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Offices;
import com.example.medicine.model.Work;
import com.example.medicine.repository.WorkRepository;
import com.example.medicine.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {

    private WorkRepository workRepository;

    @Override
    public ResponseEntity<List<Work>> findAll() {
        return ResponseEntity.ok().body(workRepository.findAll());
    }

    @Override
    public ResponseEntity<Work> findById(Long id) {
        return workRepository.findById(id)
                .map(work -> ResponseEntity.ok().body(work))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Work> save(Work work) {
        return ResponseEntity.ok().body(workRepository.save(work));
    }

    @Override
    public ResponseEntity<Work> putSave(Long id, Work work) {
        return workRepository.findById(id)
                .map(workSave -> {
                    workSave.setWork_id(id);
                    workSave.setStart_time(work.getStart_time());
                    workSave.setStop_time(work.getStop_time());
                    workSave.setResult_time(work.getResult_time());
                    workSave.setOffices(work.getOffices());
                    workSave.setEmployee(work.getEmployee());
                    return ResponseEntity.ok().body(workRepository.save(workSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Work> patchSave(Long id, Work work) {
        return workRepository.findById(id)
                .map(workSave -> {
                    if(work.getStart_time() != null){
                        workSave.setStart_time(work.getStart_time());
                    }
                    if(work.getStop_time() != null){
                        workSave.setStop_time(work.getStop_time());
                    }
                    if(work.getResult_time() != null){
                        workSave.setResult_time(work.getResult_time());
                    }
                    if(work.getOffices() != null){
                        Offices offices = new Offices();
                        if(work.getOffices().getNumber_offices() != null){
                            offices.setNumber_offices(
                                    work.getOffices().getNumber_offices());
                        }
                        workSave.setOffices(offices);
                    }
                    if(work.getEmployee() != null){
                        Employee employee = new Employee();
                        if(work.getEmployee().getEmployee_name() != null){
                            employee.setEmployee_name(
                                    work.getEmployee().getEmployee_name());
                        }
                        workSave.setEmployee(employee);
                    }
                    return ResponseEntity.ok().body(workRepository.save(workSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Work> delete(Long id) {
        return workRepository.findById(id)
                .map(work -> {
                    workRepository.deleteById(work.getWork_id());
                    return ResponseEntity.ok().body(work);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
