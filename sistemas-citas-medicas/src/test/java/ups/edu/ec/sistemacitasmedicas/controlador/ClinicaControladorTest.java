package ups.edu.ec.sistemacitasmedicas.controlador;




import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ups.edu.ec.sistemacitasmedicas.modelo.Clinica;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.servicio.ClinicaServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.MedicioServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.PersonaServicio;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(ClinicaControlador.class)
public class ClinicaControladorTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClinicaServicio clinicaServicio;

    @MockBean
    private PersonaServicio personaServicio;

    @MockBean
    private MedicioServicio medicoServicio;

    @Test
    public void testGuardarClinica() throws Exception {
        // Datos de prueba
        Integer medicoId = 1;
        Integer personaId = 1;
        Clinica clinica = new Clinica();
        clinica.setClinica_id(1);
        // ... Establecer otras propiedades de la clínica

        // Simular el comportamiento del servicio
        given(personaServicio.get(personaId)).willReturn(Optional.of(new Persona()));
        given(medicoServicio.get(medicoId)).willReturn(Optional.of(new Medico()));
        given(clinicaServicio.guardarprescripcion(any(Clinica.class), anyList())).willReturn(clinica);

        // Realizar la solicitud POST con el cuerpo de la clínica en formato JSON crear/{medico_id}/{persona_id}
        ResultActions response= mockMvc.perform(post("/clinica/crear/{medico_id}/{persona_id}", medicoId, personaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clinica))); // Proporcionar el contenido JSON completo de la clínica
        response.andDo(print());

    }
}
