package med.voll.api.controller;

import med.voll.api.medic.DataMedicRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicController {
    @PostMapping
    public void register(@RequestBody DataMedicRegistry dados) {

        System.out.println(dados);

    }
}
