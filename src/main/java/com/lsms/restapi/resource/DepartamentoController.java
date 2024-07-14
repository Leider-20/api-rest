package com.lsms.restapi.resource;


import com.lsms.restapi.dto.DepartamentoDTO;
import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.service.DepartamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping(value = "/departamento")
    public List<Departamento> getAll() {
        return this.service.getAll();
    }

    @GetMapping(value = "/departamento/{id}")
    public Departamento getById(@PathVariable Long id) {
        return this.service.getById(id);
    }


    @PostMapping(value = "/departamento")
    public Departamento add(@RequestBody DepartamentoDTO departamentoDTO) {
        Departamento dept = new Departamento();
        dept.setId(departamentoDTO.getId());
        dept.setNombre(departamentoDTO.getNombre());
        return this.service.add(dept);
    }

    @PutMapping(value = "/departamento/{id}", consumes = "application/json")
    public Departamento update(@PathVariable Long id, @RequestBody DepartamentoDTO departamentoDTO) {
        Departamento dept = new Departamento();
        dept.setId(departamentoDTO.getId());
        dept.setNombre(departamentoDTO.getNombre());
        return this.service.update(id, dept);
    }


    @DeleteMapping(value = "/departamento")
    public void deleteAll() {
        this.service.deleteAll();
    }

    @DeleteMapping(value = "/departamento/{id}")
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}
