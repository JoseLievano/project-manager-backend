package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBCategoryMapper implements DefaultMapper <bsPrKBCategoryDTO, bsPrKBCategoryMiniDTO, bsPrKBCategoryForm, bsPrKBCategoryEntity> {

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Override
    public bsPrKBCategoryDTO toDTO(bsPrKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrKBCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .build();

    }

    @Override
    public bsPrKBCategoryMiniDTO toSmallDTO(bsPrKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrKBCategoryMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsPrKBCategoryEntity toEntity(bsPrKBCategoryForm form) {

        if (form == null) {
            return null;
        }

        return bsPrKBCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }
}
