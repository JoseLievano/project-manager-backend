package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsDocsCategoryMapper implements DefaultMapper <bsDocsCategoryDTO, bsDocsCategoryMiniDTO, bsDocsCategoryForm, bsDocsCategoryEntity> {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public bsDocsCategoryDTO toDTO(bsDocsCategoryEntity entity) {

        if (entity == null)
            return null;

        return bsDocsCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsDocsCategoryMiniDTO toSmallDTO(bsDocsCategoryEntity entity) {

            if (entity == null)
                return null;

            return bsDocsCategoryMiniDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
    }

    @Override
    public bsDocsCategoryEntity toEntity(bsDocsCategoryForm form) {

        if (form == null)
            return null;

        return bsDocsCategoryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .build();

    }
}
