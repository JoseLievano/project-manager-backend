package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsDocMapper implements DefaultMapper <bsDocDTO, bsDocMiniDTO, bsDocListDTO, bsDocForm, bsDocEntity> {

    private final bsDocsCategoryMapper bsDocsCategoryMapper;

    private final BusinessMapper businessMapper;

    @Lazy
    @Autowired
    public bsDocMapper(bsDocsCategoryMapper bsDocsCategoryMapper, BusinessMapper businessMapper) {
        this.bsDocsCategoryMapper = bsDocsCategoryMapper;
        this.businessMapper = businessMapper;
    }

    @Override
    public bsDocDTO toDTO(bsDocEntity entity) {

        if (entity == null)
            return null;

        return bsDocDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .category(bsDocsCategoryMapper.toSmallDTO(entity.getBsDocsCategory()))
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();

    }

    @Override
    public bsDocMiniDTO toSmallDTO(bsDocEntity entity) {

        if (entity == null)
            return null;

        return bsDocMiniDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();

    }

    @Override
    public bsDocEntity toEntity(bsDocForm form) {

        if (form == null)
            return null;

        return bsDocEntity.builder()
                .id(form.getId())
                .title(form.getTitle())
                .content(form.getContent())
                .build();

    }

    @Override
    public bsDocListDTO toListDTO(bsDocEntity bsDocEntity) {

        if (bsDocEntity == null)
            return null;

        return bsDocListDTO.builder()
                .id(bsDocEntity.getId())
                .title(bsDocEntity.getTitle())
                .content(bsDocEntity.getContent())
                .category(bsDocsCategoryMapper.toSmallDTO(bsDocEntity.getBsDocsCategory()))
                .business(businessMapper.toSmallDTO(bsDocEntity.getBusiness()))
                .build();

    }
}
