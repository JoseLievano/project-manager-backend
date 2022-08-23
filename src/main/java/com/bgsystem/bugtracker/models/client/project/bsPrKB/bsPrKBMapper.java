package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBMapper implements DefaultMapper <bsPrKBDTO, bsPrKBMiniDTO, bsPrKBForm, bsPrKBEntity> {

    @Autowired
    private bsProjectMapper projectMapper;

    @Autowired
    private bsPrKBCategoryMapper categoryMapper;

    @Override
    public bsPrKBDTO toDTO(bsPrKBEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrKBDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .category(categoryMapper.toSmallDTO(entity.getCategory()))
                .build();

    }

    @Override
    public bsPrKBMiniDTO toSmallDTO(bsPrKBEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrKBMiniDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

    }

    @Override
    public bsPrKBEntity toEntity(bsPrKBForm form) {

        if (form == null) {
            return null;
        }

        return bsPrKBEntity.builder()
                .id(form.getId())
                .title(form.getTitle())
                .content(form.getContent())
                .build();
    }
}
