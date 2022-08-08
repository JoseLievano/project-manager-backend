package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsTaskCategoryMapper implements DefaultMapper <bsTaskCategoryDTO, bsTaskCategoryMiniDTO, bsTaskCategoryForm, bsTaskCategoryEntity>{

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public bsTaskCategoryDTO toDTO(bsTaskCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTaskCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsTaskCategoryMiniDTO toSmallDTO(bsTaskCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTaskCategoryMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsTaskCategoryEntity toEntity(bsTaskCategoryForm form) {

        if (form == null) {
            return null;
        }

        return bsTaskCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }
}
