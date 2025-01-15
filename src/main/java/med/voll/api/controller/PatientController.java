package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;


    @GetMapping
    public Page<DataListPatient> listar(@PageableDefault(page = 0, size = 10, sort = { "name" }) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(DataListPatient::new);
    }


    @PostMapping
    @Transactional
    public void registry(@RequestBody @Valid PatientDataRegistry data) {
        repository.save(new Patient(data));
    }


    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataPatientUpdate data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.inactive();
    }
}
