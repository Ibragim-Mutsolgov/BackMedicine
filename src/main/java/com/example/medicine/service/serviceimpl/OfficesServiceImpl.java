package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.service.OfficesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OfficesServiceImpl implements OfficesService {

    private OfficesRepository officesRepository;

    @Override
    public ResponseEntity<List<Offices>> findAll() {
        return ResponseEntity.ok().body(officesRepository.findAll());
    }

    @Override
    public ResponseEntity<Offices> findById(Long id) {
        return officesRepository.findById(id)
                .map(offices -> ResponseEntity.ok().body(offices))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> save(Offices offices) {
        return ResponseEntity.ok().body(officesRepository.save(offices));
    }

    @Override
    public ResponseEntity<Offices> putSave(Long id, Offices offices) {
        return officesRepository.findById(id)
                .map(officesSave -> {
                    officesSave.setOffices_id(id);
                    officesSave.setNumber_offices(offices.getNumber_offices());
                    return ResponseEntity.ok().body(officesRepository.save(officesSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> patchSave(Long id, Offices offices) {
        return officesRepository.findById(id)
                .map(officesSave -> {
                    officesSave.setNumber_offices(offices.getNumber_offices());
                    return ResponseEntity.ok().body(officesRepository.save(officesSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> delete(Long id) {
        return officesRepository.findById(id)
                .map(offices -> {
                    officesRepository.deleteById(offices.getOffices_id());
                    return ResponseEntity.ok().body(offices);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
