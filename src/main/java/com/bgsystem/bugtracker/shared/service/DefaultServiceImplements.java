package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class DefaultServiceImplements <DTO, MINIDTO, LISTDTO, FORM, ENTITY, ID> implements DefaultService <DTO, MINIDTO, LISTDTO, FORM, ID> {

    protected final JpaRepository<ENTITY, ID> repository;
    protected final DefaultMapper<DTO, MINIDTO, LISTDTO, FORM, ENTITY> mapper;

    public DefaultServiceImplements (JpaRepository<ENTITY, ID> repository, DefaultMapper<DTO, MINIDTO, LISTDTO, FORM, ENTITY> mapper){
        this.repository = repository;
        this.mapper = mapper;
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
    public Collection<LISTDTO> getAllForList(Optional<FilterRequest> listRequestRecord) throws ElementNotFoundException {

        List<LISTDTO> list = repository.findAll()
                .stream()
                .map(mapper::toListDTO).toList();

        return list;
    }

    @Override
    public Page<LISTDTO> getPageableList(PageableRequest pageableRequest) throws ElementNotFoundException {

        PageRequest pr = pageableRequest.getPageRequest();

        return repository.findAll(pr).map(mapper::toListDTO);


    }

    @Override
    public LISTDTO updateListView(ID id) throws ElementNotFoundException {

        ENTITY toUpdate = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        repository.save(updateListFields(toUpdate));

        return mapper.toListDTO(toUpdate);

    }

    private ENTITY updateListFields(ENTITY entity){
        return entity;
    }
}
