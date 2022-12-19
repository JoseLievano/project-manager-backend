package com.bgsystem.bugtracker.shared.controller;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

public abstract class DefaultController <DTO, MINIDTO, LISTDTO, FORM, ID>{

    protected final DefaultService<DTO, MINIDTO, LISTDTO, FORM, ID> service;

    protected DefaultController(DefaultService<DTO, MINIDTO, LISTDTO, FORM, ID> service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getOne(@PathVariable ID id) throws ElementNotFoundException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<DTO>> getAll (){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<MINIDTO> insert (@Valid @RequestBody FORM form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update (@PathVariable ID id, @Valid @RequestBody FORM form) throws ElementNotFoundException {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> delete (@PathVariable ID id) throws ElementNotFoundException {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<LISTDTO>> getAllForList(@RequestBody Optional <FilterRequest> filterRequest) throws ElementNotFoundException {

        if (filterRequest.isPresent()){
            return ResponseEntity.ok(service.getAllForList(Optional.of(filterRequest.get())));
        }else{
            return ResponseEntity.ok(service.getAllForList(Optional.empty()));
        }

    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<LISTDTO>> getAllForListPageable(@RequestBody Optional<PageableRequest> pageableRequest) throws ElementNotFoundException, BadOperator {

        System.out.println("pageable " + pageableRequest.isPresent());

        if(pageableRequest.isPresent()){

            System.out.println("in if");

            return ResponseEntity.ok(service.getPageableList(pageableRequest.get()));

        }else{
            return ResponseEntity.badRequest().build();
        }

    }

}
