package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultController<BusinessDTO, BusinessMiniDTO, BusinessForm, Long> {

    private BusinessServiceImplements service;
    protected BusinessController(BusinessServiceImplements service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/list")
    public Collection<BusinessListDTO> getAllForList() {
        return service.getAllForList();
    }

}
