package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsPriorityMapper implements DefaultMapper <bsPriorityDTO, bsPriorityMiniDTO, bsPriorityListDTO, bsPriorityForm, bsPriorityEntity> {

    private final BusinessMapper businessMapper;

    private final bsPrTaskMapper bsPrTaskMapper;

    @Lazy
    @Autowired
    public bsPriorityMapper(BusinessMapper businessMapper, bsPrTaskMapper bsPrTaskMapper) {
        this.businessMapper = businessMapper;
        this.bsPrTaskMapper = bsPrTaskMapper;
    }

    @Override
    public bsPriorityDTO toDTO(bsPriorityEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPriorityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .priorityOrder(entity.getPriorityOrder())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .tasks(entity.getTasks()
                        .stream()
                        .map(bsPrTaskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsPriorityMiniDTO toSmallDTO(bsPriorityEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsPriorityMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .priorityOrder(entity.getPriorityOrder())
                .build();

    }

    @Override
    public bsPriorityEntity toEntity(bsPriorityForm form) {

        if (form == null) {
            return null;
        }

        return bsPriorityEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .priorityOrder(form.getPriorityOrder())
                .build();

    }

    @Override
    public bsPriorityListDTO toListDTO(bsPriorityEntity entity) {

        if (entity == null)
            return null;

        return bsPriorityListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .priorityOrder(entity.getPriorityOrder())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .taskCount(entity.getTaskCount())
                .build();

    }

}
