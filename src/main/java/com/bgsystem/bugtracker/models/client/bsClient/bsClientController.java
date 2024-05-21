package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_client")
public class bsClientController extends DefaultController <bsClientDTO, bsClientMiniDTO, bsClientListDTO, bsClientForm, Long> {

    protected bsClientController(bsClientServiceImplements service) {
        super(service);
    }

}
