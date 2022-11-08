package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_status")
public class bsStatusController extends DefaultController<bsStatusDTO, bsStatusMiniDTO, bsStatusListDTO, bsStatusForm, Long> {

    public bsStatusController(bsStatusServiceImplements service){
        super(service);
    }

}
