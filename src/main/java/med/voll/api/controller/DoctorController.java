package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.medic.DataListDoctor;
import med.voll.api.medic.DataMedicRegistry;
import med.voll.api.medic.Doctor;
import med.voll.api.medic.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody DataMedicRegistry data) {
        repository.save(new Doctor(data));
    }
    @GetMapping
    public Page<DataListDoctor> read(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(DataListDoctor::new);
    }
}
