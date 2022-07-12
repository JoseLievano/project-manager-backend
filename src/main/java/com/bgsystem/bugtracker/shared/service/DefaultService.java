package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;

import java.util.Set;

public interface DefaultService < DTO, MINIDTO, FORM, ID >{

    DTO getOne(ID id) throws ElementNotFoundExeption;

    Set<DTO> getAll();

    MINIDTO insert(FORM form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails;

    DTO update(ID id, FORM form) throws ElementNotFoundExeption;

    DTO delete (ID id) throws ElementNotFoundExeption;

}
