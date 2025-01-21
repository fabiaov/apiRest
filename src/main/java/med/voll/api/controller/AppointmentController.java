package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.validations.cancel.CancelDataAppointment;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule schedule;
    @PostMapping
    @Transactional
    public ResponseEntity appointment(@RequestBody @Valid DatasSchedulingAppointment data) {
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelDataAppointment data) {
        schedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
