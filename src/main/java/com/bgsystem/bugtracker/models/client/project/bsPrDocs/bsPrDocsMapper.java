package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsPrDocsMapper implements DefaultMapper <bsPrDocsDTO, bsPrDocsMiniDTO, bsPrDocsForm, bsPrDocsEntity> {

    @Lazy
    @Autowired
    private bsPrDocsCategoryMapper categoryMapper;

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Override
    public bsPrDocsDTO toDTO(bsPrDocsEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrDocsDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .category(categoryMapper.toSmallDTO(entity.getCategory()))
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .build();

    }

    @Override
    public bsPrDocsMiniDTO toSmallDTO(bsPrDocsEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrDocsMiniDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

    }

    @Override
    public bsPrDocsEntity toEntity(bsPrDocsForm form) {

        if (form == null) {
            return null;
        }

        return bsPrDocsEntity.builder()
                .id(form.getId())
                .title(form.getTitle())
                .content(form.getContent())
                .build();

    }
}
