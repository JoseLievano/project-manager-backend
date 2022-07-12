package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController extends DefaultController<ClientDTO, ClientMiniDTO, ClientForm, Long> {

    public ClientController(DefaultService<ClientDTO, ClientMiniDTO, ClientForm, Long> service) {
        super(service);
    }

}

