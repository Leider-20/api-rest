package com.lsms.restapi.service;

import com.lsms.restapi.persistence.Departamento;
import com.lsms.restapi.persistence.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository){
        this.repository = repository;
    }

    public List<Departamento> getAll(){
        return this.repository.findAll();
    }

    public Departamento getById(Long id){
        if (this.repository.findById(id).isPresent()){
            return this.repository.findById(id).get();
        }
        throw new IllegalArgumentException("No se encontró la ID");
    }

    public Departamento add(Departamento departamento){
        return this.repository.save(departamento);
    }
    public Departamento update(Long id, Departamento departamento){
        Optional<Departamento> dept = this.repository.findById(id);
        if (dept.isPresent()){
            dept.get().setNombre(departamento.getNombre());
            return this.repository.save(dept.get());
        }
        throw new IllegalArgumentException("No se encontró la ID");
    }

    public void delete(Long id){
        this.repository.deleteById(id);
    }
}
