package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.stereotype.Service;

@Service
public class bsGeneralSettingsServiceImplements extends DefaultServiceImplements <bsGeneralSettingsDTO, bsGeneralSettingsMiniDTO, bsGeneralSettingsListDTO, bsGeneralSettingsForm, bsGeneralSettingsEntity, Long> {

    public bsGeneralSettingsServiceImplements(bsGeneralSettingsRepository repository, bsGeneralSettingsMapper mapper){
        super(repository, mapper);
    }

}
