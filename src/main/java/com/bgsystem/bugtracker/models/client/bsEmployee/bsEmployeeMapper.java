package com.bgsystem.bugtracker.models.client.bsEmployee;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsEmployeeMapper implements DefaultMapper <bsEmployeeDTO, bsEmployeeMiniDTO, bsEmployeeForm, bsEmployeeEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public bsEmployeeDTO toDTO(bsEmployeeEntity entity) {

        if (entity == null)
            return null;

        return bsEmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLogin())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsEmployeeMiniDTO toSmallDTO(bsEmployeeEntity entity) {
        if (entity == null)
            return null;

        return bsEmployeeMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .dateCreated(entity.getDateCreated())
                .lastLoginDate(entity.getLastLogin())
                .build();

    }

    @Override
    public bsEmployeeEntity toEntity(bsEmployeeForm form) {

        if (form == null)
            return null;

        return bsEmployeeEntity.bsEmployeeBuilder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .dateCreated(form.getDateCreated())
                .lastLogin(form.getLastLoginDate())
                .build();

    }
}
