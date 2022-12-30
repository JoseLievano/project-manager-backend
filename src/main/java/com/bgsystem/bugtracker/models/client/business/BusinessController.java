package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultController<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, Long> {

    private BusinessServiceImplements service;
    protected BusinessController(BusinessServiceImplements service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/test")
    public Page<BusinessListDTO> test(@RequestBody PageableRequest pageableRequest) throws BadOperator, ElementNotFoundException {
        return ResponseEntity.ok(service.test(pageableRequest)).getBody();
    }

}
