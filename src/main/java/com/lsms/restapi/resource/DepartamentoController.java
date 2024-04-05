package com.lsms.restapi.resource;


import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.service.DepartamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartamentoController {

    DepartamentoService service;

    public  DepartamentoController(DepartamentoService service){
        this.service = service;
    }

    @GetMapping(value = "/departamento")
    public List<Departamento> getAll(){
        return this.service.getAll();
    }

    @GetMapping(value = "/departamento/{id}")
    public Departamento getById(@PathVariable Long id){
        return this.service.getById(id);
    }


    @PostMapping(value = "/departamento")
    public Departamento add(Departamento departamento){
        return this.service.add(departamento);
    }

    @PutMapping(value = "/departamento/{id}", consumes = "application/json")
    public Departamento update(@PathVariable Long id, @RequestBody Departamento departamento){
        return this.service.update(id,departamento);
    }


    @DeleteMapping(value = "departamento/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}
