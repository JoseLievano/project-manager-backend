package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_manager")
public class bsManagerController extends DefaultController<bsManagerDTO, bsManagerMiniDTO, bsManagerForm, Long> {

    public bsManagerController(bsManagerServiceImplements service) {
        super(service);
    }

}
