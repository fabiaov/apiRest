package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.medic.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DataMedicRegistry> dataMedicRegistryJson;
    @Autowired
    private JacksonTester<DataDetailDoctor> dataDetailDoctorJson;
    @MockitoBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Should return cod  http 400 when information is wrong")
    @WithMockUser
    void registryFirstScenario() throws Exception {
        var response = mvc
                .perform(post("/doctors")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    @WithMockUser
    void registrySecondScenario() throws Exception {
        var dataRegistry = new DataMedicRegistry(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialties.CARDIOLOGY,
                addressData());

        when(repository.save(any())).thenReturn(new Doctor(dataRegistry));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataMedicRegistryJson.write(dataRegistry).getJson()))
                .andReturn().getResponse();

        var dataDetail = new DataDetailDoctor(
                null,
                dataRegistry.name(),
                dataRegistry.email(),
                dataRegistry.crm(),
                dataRegistry.telephone(),
                dataRegistry.Specialty(),
                new Address(dataRegistry.address())
        );
        var jsonEsperado = dataDetailDoctorJson.write(dataDetail).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }



    private AddressData addressData() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
