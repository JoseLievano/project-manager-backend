package com.bgsystem.bugtracker.shared.mapper;

public interface DefaultMapper <DTO, MINIDTO, LISTDTO, FORM, ENTITY>{

    DTO toDTO(ENTITY entity);

    MINIDTO toSmallDTO (ENTITY entity);

    ENTITY toEntity (FORM form);

    LISTDTO toListDTO (ENTITY entity);

}
