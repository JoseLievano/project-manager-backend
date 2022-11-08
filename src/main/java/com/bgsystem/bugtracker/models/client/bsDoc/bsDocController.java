package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_doc")
public class bsDocController extends DefaultController<bsDocDTO, bsDocMiniDTO, bsDocListDTO, bsDocForm, Long> {

    public bsDocController(bsDocServiceImplements service) {
        super(service);
    }

}
