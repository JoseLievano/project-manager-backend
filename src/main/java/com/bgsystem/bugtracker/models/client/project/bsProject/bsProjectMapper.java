package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsProjectMapper implements DefaultMapper <bsProjectDTO, bsProjectMiniDTO, bsProjectForm, bsProjectEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private bsClientMapper clientMapper;

    @Override
    public bsProjectDTO toDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .build();

    }

    @Override
    public bsProjectMiniDTO toSmallDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .build();

    }

    @Override
    public bsProjectEntity toEntity(bsProjectForm form) {

        if (form == null) {
            return null;
        }

        return bsProjectEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .isCompleted(form.getIsCompleted())
                .build();

    }
}