package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.DoctorsPosition;
import com.example.medicine.repository.DoctorsPositionRepository;
import com.example.medicine.service.DoctorsPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DoctorsPositionServiceImpl implements DoctorsPositionService {

    private DoctorsPositionRepository repository;


    @Override
    public ResponseEntity<List<DoctorsPosition>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @Override
    public ResponseEntity<DoctorsPosition> findById(Long id) {
        return repository.findById(id)
                .map(doctorsPosition -> ResponseEntity.ok().body(doctorsPosition))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<DoctorsPosition> save(DoctorsPosition doctorsPosition) {
        return ResponseEntity.ok().body(repository.save(doctorsPosition));
    }

    @Override
    public ResponseEntity<DoctorsPosition> putSave(Long id, DoctorsPosition doctorsPosition) {
        return repository.findById(id)
                .map(doctorsPositionSave -> {
                    doctorsPositionSave.setId(id);
                    doctorsPositionSave.setParent_id(doctorsPosition.getParent_id());
                    doctorsPositionSave.setWork_position(doctorsPosition.getWork_position());
                    doctorsPositionSave.setMed(doctorsPosition.getMed());
                    doctorsPositionSave.setDate_end(doctorsPosition.getDate_end());
                    doctorsPositionSave.setForm_30(doctorsPosition.getForm_30());
                    doctorsPositionSave.setNeed_cert(doctorsPosition.getNeed_cert());
                    doctorsPositionSave.setEduc(doctorsPosition.getEduc());
                    doctorsPositionSave.setZs(doctorsPosition.getZs());
                    doctorsPositionSave.setShow_staff_list(doctorsPosition.getShow_staff_list());
                    doctorsPositionSave.setFederal_code(doctorsPosition.getFederal_code());
                    return ResponseEntity.ok().body(repository.save(doctorsPositionSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<DoctorsPosition> patchSave(Long id, DoctorsPosition doctorsPosition) {
        return null;
    }

    @Override
    public ResponseEntity<DoctorsPosition> delete(Long id) {
        return null;
    }
}
