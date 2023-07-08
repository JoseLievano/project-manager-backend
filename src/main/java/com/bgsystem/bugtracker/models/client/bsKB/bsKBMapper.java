package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsKBMapper implements DefaultMapper <bsKBDTO, bsKBMiniDTO, bsKBListDTO, bsKBForm, bsKBEntity> {

    private final BusinessMapper businessMapper;

    private final bsKBCategoryMapper bsKBCategoryMapper;

    @Lazy
    @Autowired
    public bsKBMapper(BusinessMapper businessMapper, bsKBCategoryMapper bsKBCategoryMapper) {
        this.businessMapper = businessMapper;
        this.bsKBCategoryMapper = bsKBCategoryMapper;
    }

    @Override
    public bsKBDTO toDTO(bsKBEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .category(bsKBCategoryMapper.toSmallDTO(entity.getBsKBCategory()))
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsKBMiniDTO toSmallDTO(bsKBEntity entity) {

        if (entity == null) {
            return null;
        }

        return bsKBMiniDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

    }

    @Override
    public bsKBEntity toEntity(bsKBForm form) {

        if (form == null) {
            return null;
        }

        return bsKBEntity.builder()
                .id(form.getId())
                .title(form.getTitle())
                .content(form.getContent())
                .build();

    }

    @Override
    public bsKBListDTO toListDTO(bsKBEntity bsKBEntity) {

        if (bsKBEntity == null) {
            return null;
        }

        return bsKBListDTO.builder()
                .id(bsKBEntity.getId())
                .title(bsKBEntity.getTitle())
                .content(bsKBEntity.getContent())
                .category(bsKBCategoryMapper.toSmallDTO(bsKBEntity.getBsKBCategory()))
                .business(businessMapper.toSmallDTO(bsKBEntity.getBusiness()))
                .build();

    }
}
