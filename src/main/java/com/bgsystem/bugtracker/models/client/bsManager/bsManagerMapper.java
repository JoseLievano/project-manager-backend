package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsManagerMapper implements DefaultMapper<bsManagerDTO, bsManagerMiniDTO, bsManagerForm, bsManagerEntity> {

    private final BusinessMapper businessMapper;

    @Lazy
    @Autowired
    public bsManagerMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    @Override
    public bsManagerDTO toDTO(bsManagerEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsManagerDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .dateCreated(entity.getDateCreated())
                .lastLogin(entity.getLastLogin())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();
    }

    @Override
    public bsManagerMiniDTO toSmallDTO(bsManagerEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsManagerMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .dateCreated(entity.getDateCreated())
                .lastLogin(entity.getLastLogin())
                .build();

    }

    @Override
    public bsManagerEntity toEntity(bsManagerForm form) {

        if (form == null) {
            return null;
        }

        return bsManagerEntity.bsManagerBuilder()
                .id(form.getId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .dateCreated(form.getDateCreated())
                .lastLogin(form.getLastLogin())
                .build();

    }
}
