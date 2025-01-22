package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.DataDetailAppointment;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import med.voll.api.domain.medic.Specialties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DatasSchedulingAppointment> datasSchedulingAppointmentJson;
    @Autowired
    private JacksonTester<DataDetailAppointment> dataDetailAppointmentJson;
    @MockitoBean
    private AppointmentSchedule appointmentSchedule;
    @Test
    @DisplayName("Should return cod  http 400 when information is wrong")
    @WithMockUser
    void appointmentFirstScenario() throws Exception {
        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Should return cod  http 200 when valid information")
    @WithMockUser
    void appointmentSecondScenario() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialties.CARDIOLOGY;
        var dataDetailAppointment = new DataDetailAppointment(null, 2l, 5l, date);
        when(appointmentSchedule.schedule(any())).thenReturn(dataDetailAppointment);
        var response = mvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datasSchedulingAppointmentJson.write(
                                new DatasSchedulingAppointment(2l, 5l, date, specialty )
                        ).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = dataDetailAppointmentJson.write(
                dataDetailAppointment
        ).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

}