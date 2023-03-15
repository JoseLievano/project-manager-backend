package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class DefaultServiceImplements <DTO, MINIDTO, LISTDTO, FORM, ENTITY, ID> implements DefaultService <DTO, MINIDTO, LISTDTO, FORM, ID> {

    protected final DefaultRepository<ENTITY, ID> repository;

    protected final DefaultMapper<DTO, MINIDTO, LISTDTO, FORM, ENTITY> mapper;
    protected final CommonPathExpression<ENTITY> commonPathExpression;

    public DefaultServiceImplements (DefaultRepository<ENTITY, ID> repository, DefaultMapper<DTO, MINIDTO, LISTDTO, FORM, ENTITY> mapper, CommonPathExpression<ENTITY> commonPathExpression){
        this.repository = repository;
        this.mapper = mapper;
        this.commonPathExpression = commonPathExpression;
    }


    @Override
    public DTO getOne(ID id) throws ElementNotFoundException {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public Collection<DTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public MINIDTO insert(FORM form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {
        return mapper.toSmallDTO( repository.save(mapper.toEntity( form ) ) );
    }

    @Override
    public DTO update(ID id, FORM uform) throws ElementNotFoundException {

        ENTITY toUpdate = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);

    }

    @Override
    public DTO delete(ID id) throws ElementNotFoundException {

        ENTITY toDelete = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        repository.delete(toDelete);

        return mapper.toDTO(toDelete);

    }

    @Override
    public Collection<LISTDTO> getAllListView(Optional<FilterRequest> listRequestRecord) throws ElementNotFoundException {

        return  repository.findAll()
                .stream()
                .map(mapper::toListDTO).toList();

    }

    @Override
    public Page<DTO> getPageable(PageableRequest pageRequest) throws ElementNotFoundException, BadOperator {

        PageRequest pr = pageRequest.getPageRequest();

        if (pageRequest.getFilter().isPresent()){

            ArrayList<FilterRequest> filters = pageRequest.getFilter().get();

            commonPathExpression.setFilters(filters);

            BooleanExpression expression = commonPathExpression.getExpression();

            System.out.println(expression);

            return repository.findAll(expression, pr).map(mapper::toDTO);

        }else{

            /*throw new IllegalArgumentException("Filter is not present");*/
            return repository.findAll(pr).map(mapper::toDTO);

        }

    }

    @Override
    public Page<LISTDTO> getPageableListView(PageableRequest pageRequest) throws BadOperator {

        PageRequest pr = pageRequest.getPageRequest();

        if (pageRequest.getFilter().isPresent()) {

            ArrayList<FilterRequest> filters = pageRequest.getFilter().get();

            commonPathExpression.setFilters(filters);

            BooleanExpression expression = commonPathExpression.getExpression();

            Page<ENTITY> entities = repository.findAll(expression, pr);

            for (ENTITY entity : entities) {
                updateListFields(entity);
            }

            return entities.map(mapper::toListDTO);

        }else{

            Page<ENTITY> entities = repository.findAll(pr);

            return entities.map(mapper::toListDTO);

        }

    }

    @Override
    public LISTDTO updateListView(ID id) throws ElementNotFoundException {

        ENTITY toUpdate = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        repository.save(updateListFields(toUpdate));

        return mapper.toListDTO(toUpdate);

    }

    protected ENTITY updateListFields(ENTITY entity){
        return entity;
    }
}
