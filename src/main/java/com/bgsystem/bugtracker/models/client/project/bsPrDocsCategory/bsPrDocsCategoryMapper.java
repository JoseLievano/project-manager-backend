package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsMapper;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsPrDocsCategoryMapper implements DefaultMapper <bsPrDocsCategoryDTO, bsPrDocsCategoryMiniDTO, bsPrDocsCategoryForm, bsPrDocsCategoryEntity> {

    @Lazy
    @Autowired
    private bsProjectMapper projectMapper;

    @Lazy
    @Autowired
    private bsPrDocsMapper docsMapper;

    @Override
    public bsPrDocsCategoryDTO toDTO(bsPrDocsCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrDocsCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .project(projectMapper.toSmallDTO(entity.getProject()))
                .docs(entity.getDocs().stream()
                        .map(docsMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public bsPrDocsCategoryMiniDTO toSmallDTO(bsPrDocsCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPrDocsCategoryMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsPrDocsCategoryEntity toEntity(bsPrDocsCategoryForm form) {

        if (form == null) {
            return null;
        }

        return bsPrDocsCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();
    }
}
