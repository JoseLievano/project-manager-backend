package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsKBCategoryMapper implements DefaultMapper <bsKBCategoryDTO, bsKBCategoryMiniDTO, bsKBCategoryForm, bsKBCategoryEntity> {

    private final BusinessMapper businessMapper;

    @Lazy
    @Autowired
    public bsKBCategoryMapper(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    @Override
    public bsKBCategoryDTO toDTO(bsKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsKBCategoryMiniDTO toSmallDTO(bsKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBCategoryMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsKBCategoryEntity toEntity(bsKBCategoryForm form) {

        if (form == null) {
            return null;
        }

        return bsKBCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }
}
