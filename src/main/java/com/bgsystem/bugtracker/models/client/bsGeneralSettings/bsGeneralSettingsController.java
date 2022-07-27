package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_general_settings")
public class bsGeneralSettingsController extends DefaultController<bsGeneralSettingsDTO, bsGeneralSettingsMiniDTO, bsGeneralSettingsForm, Long> {

    protected bsGeneralSettingsController(bsGeneralSettingsServiceImplements service) {
        super(service);
    }

}
