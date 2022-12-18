package com.bgsystem.bugtracker.shared.service;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface DefaultService < DTO, MINIDTO, LISTDTO, FORM, ID >{

    DTO getOne(ID id) throws ElementNotFoundException;

    Collection<DTO> getAll();

    MINIDTO insert(FORM form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails;

    DTO update(ID id, FORM form) throws ElementNotFoundException;

    DTO delete (ID id) throws ElementNotFoundException;

    Collection<LISTDTO> getAllForList(Optional<FilterRequest> filterRequest) throws ElementNotFoundException;

    Page<LISTDTO> getPageableList(PageableRequest pageableRequest) throws ElementNotFoundException, BadOperator;

    LISTDTO updateListView(ID id) throws ElementNotFoundException;

}
