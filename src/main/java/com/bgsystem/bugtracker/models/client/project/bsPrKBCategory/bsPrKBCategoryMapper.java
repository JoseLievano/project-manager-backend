package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsPrKB.bsPrKBMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsPrKBCategoryMapper implements DefaultMapper <bsPrKBCategoryDTO, bsPrKBCategoryMiniDTO, bsPrKBCategoryForm, bsPrKBCategoryEntity> {

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Lazy
    @Autowired
    private bsPrKBMapper kbMapper;

    @Override
    public bsPrKBCategoryDTO toDTO(bsPrKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrKBCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .kbs(entity.getKbs().stream()
                        .map(kbMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
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