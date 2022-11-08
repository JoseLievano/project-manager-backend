package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface DefaultService < DTO, MINIDTO, LISTDTO, FORM, ID >{

    DTO getOne(ID id) throws ElementNotFoundExeption;

    Collection<DTO> getAll();

    MINIDTO insert(FORM form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails;

    DTO update(ID id, FORM form) throws ElementNotFoundExeption;

    DTO delete (ID id) throws ElementNotFoundExeption;

    Collection<LISTDTO> getAllForList(Optional<FilterRequest> filterRequest) throws ElementNotFoundExeption;

    Page<LISTDTO> getPageableList(PageableRequest pageableRequest) throws ElementNotFoundExeption;

    LISTDTO updateListView(ID id) throws ElementNotFoundExeption;

}
