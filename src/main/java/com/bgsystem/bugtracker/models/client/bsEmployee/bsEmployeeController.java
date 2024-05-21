package com.bgsystem.bugtracker.models.client.bsEmployee;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_employee")
public class bsEmployeeController extends DefaultController<bsEmployeeDTO, bsEmployeeMiniDTO, bsEmployeeListDTO, bsEmployeeForm, Long> {

    public bsEmployeeController(bsEmployeeServiceImplements service) {
        super(service);
    }

}
