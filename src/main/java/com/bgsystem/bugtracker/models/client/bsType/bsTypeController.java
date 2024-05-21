package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_type")
public class bsTypeController extends DefaultController <bsTypeDTO, bsTypeMiniDTO, bsTypeListDTO, bsTypeForm, Long> {

    public bsTypeController(bsTypeServiceImplements service){
        super(service);
    }

}
