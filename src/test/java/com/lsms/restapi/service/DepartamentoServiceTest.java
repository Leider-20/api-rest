package com.lsms.restapi.service;

import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.persistence.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;
    @InjectMocks
    private DepartamentoService departamentoService;

    private Departamento departamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamento = new Departamento();
        departamento.setId(10L);
        departamento.setNombre("Antioquia");
    }

    @Test
    void getAll() {
        when(departamentoRepository.findAll()).thenReturn(Collections.singletonList(departamento));
        assertNotNull(departamentoService.getAll()); //Traerá los valores que no sean nulos
        System.out.println(departamento);
    }

    @Test
    void add() {
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoService.add(departamento);

        assertNotNull(savedDepartamento, "El departamento guardado no debería ser nulo");
        assertEquals("Antioquia", savedDepartamento.getNombre(), "El nombre del departamento guardado no coincide");

        verify(departamentoRepository, times(1)).save((departamento));
        System.out.println("El departamento registrado es: "+departamento);
    }

    @Test
    void getById() {
        when(departamentoRepository.findById((10L))).thenReturn(Optional.of(departamento));
        Departamento departamentoObtenido = departamentoService.getById(10L);

        verify(departamentoRepository, times(1)).findById((10L));
        assertNotNull(departamentoObtenido);
        assertEquals("Antioquia", departamentoObtenido.getNombre());

        System.out.println(departamento);
    }

    @Test
    void update() {
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(departamento);

        Departamento savedDepartamento = departamentoService.add(departamento);

        assertNotNull(savedDepartamento, "El departamento guardado no debería ser nulo");
        assertEquals(departamento.getNombre(), savedDepartamento.getNombre(), "El nombre del modificado guardado no coincide");
        assertEquals(departamento.getId(), savedDepartamento.getId(), "El ID del departamento modificado no coincide");

        verify(departamentoRepository, times(1)).save((departamento));
        System.out.println("El departamento registrado es: "+departamento);
    }

    @Test
    void delete() {

        when(departamentoRepository.findById((10L))).thenReturn(Optional.of(new Departamento()));
        departamentoService.delete(10L);

        verify(departamentoRepository, times(1)).deleteById((10L));
        verify(departamentoRepository, never()).findById((10L));
        verify(departamentoRepository, times(1)).deleteById((10L));
        System.out.println("Id del departamento eliminado es:"+10L);

    }
}
