package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.bsType.bsTypeMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsTaskCategoryMapper implements DefaultMapper <bsTaskCategoryDTO, bsTaskCategoryMiniDTO, bsTaskCategoryListDTO, bsTaskCategoryForm, bsTaskCategoryEntity>{

    private final BusinessMapper businessMapper;

    private final bsPrTaskMapper bsPrTaskEntityMapper;

    private final bsTypeMapper bsTypeMapper;
    @Lazy
    @Autowired
    public bsTaskCategoryMapper(BusinessMapper businessMapper, bsPrTaskMapper bsPrTaskEntityMapper, bsTypeMapper bsTypeMapper) {
        this.businessMapper = businessMapper;
        this.bsPrTaskEntityMapper = bsPrTaskEntityMapper;
        this.bsTypeMapper = bsTypeMapper;
    }

    @Override
    public bsTaskCategoryDTO toDTO(bsTaskCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTaskCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .types(entity.getTypes()
                        .stream()
                        .map(bsTypeMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .tasks(entity.getTasks()
                        .stream()
                        .map(bsPrTaskEntityMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
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

    @Override
    public bsTaskCategoryListDTO toListDTO(bsTaskCategoryEntity entity) {

        if (entity == null)
            return null;

        return bsTaskCategoryListDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .types(entity.getTypesCount())
                .tasks(entity.getTaskCount())
                .build();
    }

}
