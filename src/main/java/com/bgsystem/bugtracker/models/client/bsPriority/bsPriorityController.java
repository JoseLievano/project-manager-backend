package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_priority")
public class bsPriorityController extends DefaultController <bsPriorityDTO, bsPriorityMiniDTO, bsPriorityListDTO, bsPriorityForm, Long> {

    public bsPriorityController(bsPriorityServiceImplements service) {
        super(service);
    }

}
