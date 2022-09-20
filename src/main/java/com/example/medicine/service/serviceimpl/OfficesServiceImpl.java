package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.restcontroller.OfficesRestController;
import com.example.medicine.service.OfficesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
                .map(offices -> {
                    addHateos(offices);
                    return ResponseEntity.ok().body(offices);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> save(Offices offices) {
        offices = officesRepository.save(offices);
        addHateos(offices);
        return ResponseEntity.ok().body(offices);
    }

    @Override
    public ResponseEntity<Offices> putSave(Long id, Offices offices) {
        return officesRepository.findById(id)
                .map(officesSave -> {
                    officesSave.setOffices_id(id);
                    officesSave.setNumber_offices(offices.getNumber_offices());
                    addHateos(officesSave);
                    return ResponseEntity.ok().body(officesRepository.save(officesSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> patchSave(Long id, Offices offices) {
        return officesRepository.findById(id)
                .map(officesSave -> {
                    officesSave.setNumber_offices(offices.getNumber_offices());
                    addHateos(officesSave);
                    return ResponseEntity.ok().body(officesRepository.save(officesSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Offices> delete(Long id) {
        return officesRepository.findById(id)
                .map(offices -> {
                    addHateos(offices);
                    officesRepository.deleteById(offices.getOffices_id());
                    return ResponseEntity.ok().body(offices);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Offices addHateos(Offices offices) {
        offices.add(
                linkTo(methodOn(OfficesRestController.class)
                        .findById(offices.getOffices_id())).withRel("findById"),
                linkTo(methodOn(OfficesRestController.class)
                        .save(offices)).withRel("save"),
                linkTo(methodOn(OfficesRestController.class)
                        .putSave(offices.getOffices_id(), offices)).withRel("putSave"),
                linkTo(methodOn(OfficesRestController.class)
                        .patchSave(offices.getOffices_id(), offices)).withRel("patchSave"),
                linkTo(methodOn(OfficesRestController.class)
                        .deleteById(offices.getOffices_id())).withRel("deleteById")
        );
        return offices;
    }
}
