package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.*;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface DefaultService < DTO, MINIDTO, LISTDTO, FORM, ID >{

    DTO getOne(ID id) throws ElementNotFoundException;

    Collection<DTO> getAll();

    MINIDTO insert(FORM form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails;

    DTO update(ID id, FORM form) throws ElementNotFoundException, InvalidInsertDeails;

    DTO delete (ID id) throws ElementNotFoundException, InvalidDeleteOperation;

    Collection<LISTDTO> getAllListView(Optional<FilterRequest> filterRequest) throws ElementNotFoundException;

    Page<DTO> getPageable(PageableRequest pageableRequest) throws ElementNotFoundException, BadOperator;

    Page<LISTDTO> getPageableListView(PageableRequest pageableRequest) throws ElementNotFoundException, BadOperator;

    LISTDTO updateListView(ID id) throws ElementNotFoundException;

}
