package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsGeneralSettingsServiceImplements extends DefaultServiceImplements <bsGeneralSettingsDTO, bsGeneralSettingsMiniDTO, bsGeneralSettingsListDTO, bsGeneralSettingsForm, bsGeneralSettingsEntity, Long> {

    private final bsGeneralSettingsPredicate bsGeneralSettingsPredicate;

    @Autowired
    public bsGeneralSettingsServiceImplements(bsGeneralSettingsRepository repository, bsGeneralSettingsMapper mapper, bsGeneralSettingsPredicate bsGeneralSettingsPredicate) {
        super(repository, mapper, bsGeneralSettingsPredicate);
        this.bsGeneralSettingsPredicate = bsGeneralSettingsPredicate;
    }

}
