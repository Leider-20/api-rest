package com.lsms.restapi.service;

import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.persistence.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    DepartamentoRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;  // Esta clase "JdbcTemplate" sirve para poder ejecutar comandos SQL aquí en java. La usamos para reinciar los índices de la tabla en la base de datos cuando se borren los datos de esta.

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public List<Departamento> getAll() {
        return this.repository.findAll();
    }

    public Departamento getById(Long id) {
        Optional<Departamento> dept = this.repository.findById(id);
        if (dept.isPresent()) {
            return dept.get();
        }
        throw new IllegalArgumentException("No se encontró la ID");
    }

    public Departamento add(Departamento departamento) {
        return this.repository.save(departamento);
    }

    public Departamento update(Long id, Departamento departamento) {
        Optional<Departamento> dept = this.repository.findById(id);
        if (dept.isPresent()) {
            dept.get().setNombre(departamento.getNombre());
            return this.repository.save(dept.get());
        }
        throw new IllegalArgumentException("No se encontró la ID");
    }


    // Creamos el método "reseedIndex" para reiniciar los índices de la tabla de la base de datos usando el objeto creado "jdbcTemplate" de la clase "JdbcTemplate" para ejecutar el comando SQL aquí desde java.
    private void reseedIndex() {
        jdbcTemplate.execute("DBCC CHECKIDENT ('departamento', RESEED, 0)");
    }

    public void deleteAll() {
        this.repository.deleteAll();
        reseedIndex();
    }


    public void delete(Long id) {
        this.repository.deleteById(id);
    }



}
