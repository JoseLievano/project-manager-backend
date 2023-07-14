package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsStatusMapper implements DefaultMapper<bsStatusDTO, bsStatusMiniDTO, bsStatusListDTO, bsStatusForm, bsStatusEntity> {

    private final BusinessMapper businessMapper;

    private final bsPrTaskMapper bsPrTaskMapper;


    @Lazy
    @Autowired
    public bsStatusMapper(BusinessMapper businessMapper, bsPrTaskMapper bsPrTaskMapper) {
        this.businessMapper = businessMapper;
        this.bsPrTaskMapper = bsPrTaskMapper;
    }

    @Override
    public bsStatusDTO toDTO(bsStatusEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsStatusDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .color(entity.getColor())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .tasks(entity.getTasks()
                        .stream()
                        .map(bsPrTaskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsStatusMiniDTO toSmallDTO(bsStatusEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsStatusMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .color(entity.getColor())
                .build();

    }

    @Override
    public bsStatusEntity toEntity(bsStatusForm form) {

        if (form == null) {
            return null;
        }

        return bsStatusEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .color(form.getColor())
                .build();

    }

    @Override
    public bsStatusListDTO toListDTO(bsStatusEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsStatusListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .color(entity.getColor())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .tasks(entity.getTaskCount())
                .build();
    }
}
