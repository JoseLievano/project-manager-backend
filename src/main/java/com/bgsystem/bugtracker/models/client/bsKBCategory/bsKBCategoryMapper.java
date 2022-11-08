package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.models.client.bsKB.bsKBMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsKBCategoryMapper implements DefaultMapper <bsKBCategoryDTO, bsKBCategoryMiniDTO, bsKBCategoryListDTO, bsKBCategoryForm, bsKBCategoryEntity> {

    private final BusinessMapper businessMapper;

    private final bsKBMapper bsKBMapper;

    @Lazy
    @Autowired
    public bsKBCategoryMapper(BusinessMapper businessMapper, bsKBMapper bsKBMapper) {
        this.businessMapper = businessMapper;
        this.bsKBMapper = bsKBMapper;
    }

    @Override
    public bsKBCategoryDTO toDTO(bsKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .bsKBs(entity.getBsKBEntities()
                        .stream()
                        .map(bsKBMapper::toSmallDTO)
                        .collect(java.util.stream.Collectors.toSet())
                )
                .build();

    }

    @Override
    public bsKBCategoryMiniDTO toSmallDTO(bsKBCategoryEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBCategoryMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

    @Override
    public bsKBCategoryEntity toEntity(bsKBCategoryForm form) {

        if (form == null) {
            return null;
        }

        return bsKBCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }

    @Override
    public bsKBCategoryListDTO toListDTO(bsKBCategoryEntity bsKBCategoryEntity) {

        if (bsKBCategoryEntity == null)
            return null;

        return bsKBCategoryListDTO.builder()
                .id(bsKBCategoryEntity.getId())
                .name(bsKBCategoryEntity.getName())
                .business(businessMapper.toSmallDTO(bsKBCategoryEntity.getBusiness()))
                .bsKBCount(bsKBCategoryEntity.getBsKBCount())
                .build();

    }
}
