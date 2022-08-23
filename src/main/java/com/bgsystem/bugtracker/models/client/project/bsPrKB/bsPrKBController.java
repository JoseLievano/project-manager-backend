package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_kb")
public class bsPrKBController extends DefaultController <bsPrKBDTO, bsPrKBMiniDTO, bsPrKBForm, Long> {

    public bsPrKBController (bsPrKBServiceImplements service) {
        super (service);
    }

}
