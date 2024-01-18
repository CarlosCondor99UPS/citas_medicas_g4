package ups.edu.ec.sistemacitasmedicas.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.repositorio.PersonaRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.impl.PersonaServicioImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {

    @Mock
    private PersonaRepositorio personaRepositorio;

    @InjectMocks
    private PersonaServicioImpl personaServicio;

    private Persona persona;

    @Test
    public void testGuardarEmpleado(){

        Persona persona = new Persona("0100324534", "santiago", "cabrera", "Gualaceo", "0998087966", "ssc@gmail.com","activo",
                "17-02-2002", "masculino", "Empleado");

//        given(personaRepositorio.findByCedula(persona.getCedula())).willReturn(persona);

        given(personaRepositorio.save(persona)).willReturn(persona);

        Persona personaGuardado = personaServicio.crearPersona(persona);

        assertThat(personaGuardado);

    }



}
