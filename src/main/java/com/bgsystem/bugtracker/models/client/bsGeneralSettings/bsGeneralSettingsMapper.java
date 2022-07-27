package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.models.client.business.BusinessMapper;
import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class bsGeneralSettingsMapper implements DefaultMapper<bsGeneralSettingsDTO, bsGeneralSettingsMiniDTO, bsGeneralSettingsForm, bsGeneralSettingsEntity > {

    @Lazy
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public bsGeneralSettingsDTO toDTO(bsGeneralSettingsEntity entity) {
        if (entity == null)
            return null;

        return bsGeneralSettingsDTO.builder()
                .id(entity.getId())
                .logoUrl(entity.getLogoUrl())
                .address(entity.getAddress())
                .website(entity.getWebsite())
                .email(entity.getEmail())
                .business(businessMapper.toSmallDTO(entity.getBusiness()))
                .build();
    }

    @Override
    public bsGeneralSettingsMiniDTO toSmallDTO(bsGeneralSettingsEntity entity) {
        if (entity == null)
            return null;

        return bsGeneralSettingsMiniDTO.builder()
                .id(entity.getId())
                .logoUrl(entity.getLogoUrl())
                .address(entity.getAddress())
                .website(entity.getWebsite())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public bsGeneralSettingsEntity toEntity(bsGeneralSettingsForm form) {
        if (form == null)
            return null;

        return bsGeneralSettingsEntity.builder()
                .id(form.getId())
                .logoUrl(form.getLogoUrl())
                .address(form.getAddress())
                .website(form.getWebsite())
                .email(form.getEmail())
                .build();
    }
}
