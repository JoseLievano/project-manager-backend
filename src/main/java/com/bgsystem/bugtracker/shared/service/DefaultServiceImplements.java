package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DefaultServiceImplements <DTO, MINIDTO, FORM, ENTITY, ID> implements DefaultService <DTO, MINIDTO, FORM, ID> {

    protected final JpaRepository<ENTITY, ID> repository;
    protected final DefaultMapper<DTO, MINIDTO, FORM, ENTITY> mapper;

    public DefaultServiceImplements (JpaRepository<ENTITY, ID> repository, DefaultMapper<DTO, MINIDTO, FORM, ENTITY> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DTO getOne(ID id) throws ElementNotFoundExeption {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundExeption::new);
    }

    @Override
    public Set<DTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public MINIDTO insert(FORM form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {
        return mapper.toSmallDTO( repository.save(mapper.toEntity( form ) ) );
    }

    @Override
    public DTO update(ID id, FORM uform) throws ElementNotFoundExeption {
        ENTITY toUpdate = repository.findById(id).orElseThrow(ElementNotFoundExeption::new);

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);

    }

    @Override
    public DTO delete(ID id) throws ElementNotFoundExeption {

        ENTITY toDelete = repository.findById(id).orElseThrow(ElementNotFoundExeption::new);

        repository.delete(toDelete);

        return mapper.toDTO(toDelete);

    }

}
