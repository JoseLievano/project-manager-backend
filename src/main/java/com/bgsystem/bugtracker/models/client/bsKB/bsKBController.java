package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_kb")
public class bsKBController extends DefaultController<bsKBDTO, bsKBMiniDTO, bsKBForm, Long> {

    public bsKBController(bsKBServiceImplements service) {
        super(service);
    }

}
