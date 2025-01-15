package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.medic.DataMedicRegistry;
import med.voll.api.medic.Doctor;
import med.voll.api.medic.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
