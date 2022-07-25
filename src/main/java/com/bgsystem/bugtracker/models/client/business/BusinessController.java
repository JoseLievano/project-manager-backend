package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultController<BusinessDTO, BusinessMiniDTO, BusinessForm, Long> {

    protected BusinessController(DefaultService<BusinessDTO, BusinessMiniDTO, BusinessForm, Long> service) {
        super(service);
    }

}
