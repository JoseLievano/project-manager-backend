package com.bgsystem.bugtracker.shared.controller;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

public abstract class DefaultController <DTO, MINIDTO, FORM, ID>{

    protected final DefaultService<DTO, MINIDTO, FORM, ID> service;

    protected DefaultController(DefaultService<DTO, MINIDTO, FORM, ID> service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getOne(@PathVariable ID id) throws ElementNotFoundExeption {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Set<DTO>> getAll (){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<MINIDTO> insert (@Valid @RequestBody FORM form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {
        System.out.println("Ingresamos en el post");
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update (@PathVariable ID id, @Valid @RequestBody FORM form) throws ElementNotFoundExeption {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> delete (@PathVariable ID id) throws ElementNotFoundExeption {
        return ResponseEntity.ok(service.delete(id));
    }

}
