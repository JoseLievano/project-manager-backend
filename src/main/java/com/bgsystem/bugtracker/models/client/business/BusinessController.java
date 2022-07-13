package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultServiceImplements <BusinessDTO, BusinessMiniDTO, BusinessForm, BusinessEntity, Long> {


    public BusinessController(BusinessRepository repository, BusinessMapper mapper) {
        super(repository, mapper);
    }

}
