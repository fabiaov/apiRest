package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;


    @GetMapping
    public ResponseEntity<Page<DataListPatient>> listar(@PageableDefault(page = 0, size = 10, sort = { "name" }) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataListPatient::new);
        return ResponseEntity.ok(page);
    }


    @PostMapping
    @Transactional
    public ResponseEntity registry(@RequestBody @Valid PatientDataRegistry data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        repository.save(patient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataPatientDetail(patient));
    }


    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataPatientUpdate data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);
        return ResponseEntity.ok(new DataPatientDetail(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.inactive();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail (@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataPatientDetail(patient));
    }
}
