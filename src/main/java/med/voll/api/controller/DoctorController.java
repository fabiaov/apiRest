package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataMedicRegistry data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailDoctor(doctor));
    }
    @GetMapping
    public ResponseEntity<Page<DataListDoctor>> read(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page =  repository.findAllByActiveTrue(pagination).map(DataListDoctor::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataMedicUpdate data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfo(data);
        return ResponseEntity.ok(new DataDetailDoctor(doctor));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detail(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailDoctor(doctor));
    }
}
