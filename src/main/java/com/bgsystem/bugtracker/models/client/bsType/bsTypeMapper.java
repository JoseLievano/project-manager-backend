package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsTypeMapper implements DefaultMapper<bsTypeDTO, bsTypeMiniDTO, bsTypeListDTO, bsTypeForm, bsTypeEntity> {

    private final BusinessMapper businessMapper;

    private final bsPrTaskMapper bsPrTaskMapper;

    private final bsTaskCategoryMapper bsTaskCategoryMapper;

    @Lazy
    @Autowired
    public  bsTypeMapper(BusinessMapper businessMapper, bsPrTaskMapper bsPrTaskMapper, bsTaskCategoryMapper bsTaskCategoryMapper) {
        this.businessMapper = businessMapper;
        this.bsPrTaskMapper = bsPrTaskMapper;
        this.bsTaskCategoryMapper = bsTaskCategoryMapper;
    }

    @Override
    public bsTypeDTO toDTO(bsTypeEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .taskCategories(entity.getTaskCategories().stream()
                        .map(bsTaskCategoryMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .tasks(entity.getTasks().stream()
                        .map(bsPrTaskMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsTypeMiniDTO toSmallDTO(bsTypeEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsTypeMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsTypeEntity toEntity(bsTypeForm form) {

        if (form == null) {
            return null;
        }

        return bsTypeEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }

    @Override
    public bsTypeListDTO toListDTO(bsTypeEntity entity) {

            if (entity == null)
                return null;

            return bsTypeListDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .business(businessMapper.toSmallDTO(entity.getBusiness()))
                    .taskCategories(entity.getTaskCategoriesCount())
                    .taskCount(entity.getTaskCount())
                    .build();

    }
}
