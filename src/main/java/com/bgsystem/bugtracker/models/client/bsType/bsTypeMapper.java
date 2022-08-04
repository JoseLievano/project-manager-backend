package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsTypeMapper implements DefaultMapper<bsTypeDTO, bsTypeMiniDTO, bsTypeForm, bsTypeEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public bsTypeDTO toDTO(bsTypeEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsTypeMiniDTO toSmallDTO(bsTypeEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTypeMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsTypeEntity toEntity(bsTypeForm form) {

        if (form == null) {
            return null;
        }

        return bsTypeEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }
}
