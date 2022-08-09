package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsProjectMapper implements DefaultMapper <bsProjectDTO, bsProjectMiniDTO, bsProjectForm, bsProjectEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Lazy
    @Autowired
    private bsClientMapper clientMapper;

    @Lazy
    @Autowired
    private bsPrTaskMapper taskMapper;

    @Override
    public bsProjectDTO toDTO(bsProjectEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsProjectDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.getIsCompleted())
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .client(clientMapper.toSmallDTO(entity.getClient()))
                .tasks(entity.getTasks().stream()
                        .map(taskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
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
                .created(entity.getCreated())
                .dueDate(entity.getDueDate())
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
                .created(form.getCreated())
                .dueDate(form.getDueDate())
                .build();

    }
}
