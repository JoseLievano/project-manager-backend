package com.bgsystem.bugtracker.shared.controller;

import com.bgsystem.bugtracker.exeptions.*;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
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
    public ResponseEntity<DTO> update (@PathVariable ID id, @Valid @RequestBody FORM form) throws ElementNotFoundException, InvalidInsertDeails {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> delete (@PathVariable ID id) throws ElementNotFoundException, InvalidDeleteOperation {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/list")
    public ResponseEntity<Collection<LISTDTO>> getAllForList(@RequestBody Optional <FilterRequest> filterRequest) throws ElementNotFoundException {

        if (filterRequest.isPresent()){
            return ResponseEntity.ok(service.getAllListView(Optional.of(filterRequest.get())));
        }else{
            return ResponseEntity.ok(service.getAllListView((Optional.empty())));
        }

    }

    @PostMapping("/page")
    public ResponseEntity<Page<DTO>> getAllPageable(@RequestBody Optional<PageableRequest> pageableRequest) throws ElementNotFoundException, BadOperator {

        if(pageableRequest.isPresent()){
            return ResponseEntity.ok(service.getPageable(pageableRequest.get()));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/page-list-view")
    public ResponseEntity<Page<LISTDTO>> getAllForListPageable(@RequestBody Optional<PageableRequest> pageableRequest) throws ElementNotFoundException, BadOperator {

        if(pageableRequest.isPresent()){
            return ResponseEntity.ok(service.getPageableListView(pageableRequest.get()));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
}
