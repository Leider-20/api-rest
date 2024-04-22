package com.lsms.restapi.resource;

import com.lsms.restapi.dto.DepartamentoDTO;
import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class DepartamentoControllerTest {
    @Mock
    private DepartamentoService departamentoService;

    @InjectMocks
    private DepartamentoController departamentoController;

    private DepartamentoDTO departamentoDTO;
    private Departamento departamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamento = new Departamento();
        departamento.setId(10L);
        departamento.setNombre("Antioquia");
        departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setId(departamento.getId());
        departamentoDTO.setNombre(departamento.getNombre());
    }

    @Test
    void getAll(){
        when(departamentoService.getAll()).thenReturn(Collections.singletonList(departamento));
        assertNotNull(departamentoController.getAll());
    }

    @Test
    void getById() {
        when(departamentoService.getById((10L))).thenReturn(departamento);
        Departamento departamentoObtenido = departamentoController.getById(10L);

        verify(departamentoService, times(1)).getById((10L));
        assertNotNull(departamentoObtenido);
        assertEquals(departamento.getNombre(), departamentoObtenido.getNombre());

        System.out.println(departamento);
    }

    @Test
    void add() {
        when(departamentoService.add(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoController.add(departamentoDTO);

        assertNotNull(savedDepartamento, "El departamento guardado no debería ser nulo");
        assertEquals(departamento.getNombre(), savedDepartamento.getNombre(), "El nombre del departamento guardado no coincide");

        verify(departamentoService, times(1)).add((departamento));
        System.out.println("El departamento registrado es: "+departamento);
    }

    @Test
    void update() {
        when(departamentoService.add(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoController.add(departamentoDTO);

        assertNotNull(savedDepartamento, "El departamento guardado no debería ser nulo");
        assertEquals(departamento.getNombre(), savedDepartamento.getNombre(), "El nombre del modificado guardado no coincide");
        assertEquals(departamento.getId(), savedDepartamento.getId(), "El ID del departamento modificado no coincide");

        verify(departamentoService, times(1)).add((departamento));
        System.out.println("El departamento registrado es: "+departamento);
    }

    @Test
    void delete() {
        when(departamentoService.getById((10L))).thenReturn(new Departamento());
        departamentoController.delete(10L);

        verify(departamentoService, times(1)).delete((10L));
        verify(departamentoService, never()).getById((10L));
        verify(departamentoService, times(1)).delete((10L));
        System.out.println("Id del departamento eliminado es:"+10L);
    }
}
