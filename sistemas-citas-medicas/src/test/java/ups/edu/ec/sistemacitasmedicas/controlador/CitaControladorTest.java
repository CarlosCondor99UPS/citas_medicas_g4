package ups.edu.ec.sistemacitasmedicas.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ups.edu.ec.sistemacitasmedicas.modelo.*;
import ups.edu.ec.sistemacitasmedicas.servicio.CitaServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.ClinicaServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.MedicioServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.PersonaServicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitaControlador.class)
public class CitaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;
    @MockBean
    private MedicioServicio medicoServicio;
    @MockBean
    private CitaServicio citaServicio;


    @Autowired
    private ObjectMapper objectMapper;

    /*@Test
    public void testGuardarUsuario() throws Exception {
        // Datos de prueba
        Cita cita = new Cita();
        cita.setCita_id(1);
        cita.setFechaCita(LocalDate.parse("2023-05-20"));
        cita.setHora(LocalTime.parse("19:00"));
        cita.setEstado(false);

        Persona persona = new Persona();
        persona.setPersona_id(1);
        // Asigna la persona a la cita
        cita.setPersona(persona);

        Medico medico = new Medico();
        medico.setMedico_id(1);
        cita.setMedico(medico);

        // Simula el comportamiento del servicio de persona
        given(personaServicio.get(1)).willReturn(Optional.of(persona));
        given(medicoServicio.get(1)).willReturn(Optional.of(medico));

        // Simula el comportamiento del servicio de usuario
        given(citaServicio.guardarCitaMedico(ArgumentMatchers.any(Cita.class),ArgumentMatchers.any(Medico.class)))
                .willReturn(cita);




        // Realiza la solicitud POST con el cuerpo de la cita en formato JSON
        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cita_id\":5,\"fechaCita\":\"2023-06-20\",\"hora\":\"19:00\",\"estado\":false,\"persona\":{\"persona_id\":1},\"medico\":{\"medico_id\":1}}"))
                .andExpect(status().isOk());
    }*/

    /*@Test
    public void testCrearCitaMedica() throws Exception {
        // Datos de prueba
        Integer medicoId = 1;
        Integer personaId = 2;
        Clinica clinica = new Clinica();
        // Configurar los datos de la clínica según tus necesidades

        // Simula el comportamiento del servicio de persona
        given(personaServicio.get(personaId)).willReturn(Optional.of(new Persona()));

        // Simula el comportamiento del servicio de médico
        given(medicoServicio.get(medicoId)).willReturn(Optional.of(new Medico()));

        // Simula el comportamiento del servicio de clínica
        given(clinicaServicio.guardarprescripcion(any(Clinica.class), anyList())).willReturn(clinica);

        // Realiza la solicitud POST con el cuerpo de la clínica en formato JSON
        mockMvc.perform(post("/crear/{medico_id}/{persona_id}", medicoId, personaId)
                        .contentType(MediaType.APPLICATION_JSON);
                        //.content("{}")) // Puedes configurar el cuerpo de la clínica según tus necesidades
                //.andExpect(status().isOk());
    }*/
}
