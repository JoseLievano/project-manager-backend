package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends DefaultController <EmployeeDTO, EmployeeMiniDTO, EmployeeListDTO, EmployeeForm, Long> {


    protected EmployeeController(DefaultService<EmployeeDTO, EmployeeMiniDTO, EmployeeListDTO, EmployeeForm, Long> service) {
        super(service);
    }

}
