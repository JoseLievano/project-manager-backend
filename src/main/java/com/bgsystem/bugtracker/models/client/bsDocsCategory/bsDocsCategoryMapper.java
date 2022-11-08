package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.models.client.bsDoc.bsDocMapper;
import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class bsDocsCategoryMapper implements DefaultMapper <bsDocsCategoryDTO, bsDocsCategoryMiniDTO, bsDocsCategoryListDTO, bsDocsCategoryForm, bsDocsCategoryEntity> {

    private final BusinessMapper businessMapper;

    private final bsDocMapper bsDocMapper;

    @Lazy
    @Autowired
    public bsDocsCategoryMapper(BusinessMapper businessMapper, bsDocMapper bsDocMapper) {
        this.businessMapper = businessMapper;
        this.bsDocMapper = bsDocMapper;
    }

    @Override
    public bsDocsCategoryDTO toDTO(bsDocsCategoryEntity entity) {

        if (entity == null)
            return null;

        return bsDocsCategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .bsDocs(entity.getBsDocs()
                        .stream()
                        .map(bsDocMapper::toSmallDTO)
                        .collect(Collectors.toSet())
                )
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

    @Override
    public bsDocsCategoryListDTO toListDTO(bsDocsCategoryEntity bsDocsCategoryEntity) {

        if (bsDocsCategoryEntity == null)
            return null;

        return bsDocsCategoryListDTO.builder()
                .id(bsDocsCategoryEntity.getId())
                .name(bsDocsCategoryEntity.getName())
                .business(businessMapper.toSmallDTO(bsDocsCategoryEntity.getBusiness()))
                .bsDocsCount(bsDocsCategoryEntity.getBsDocsCount())
                .build();

    }
}
