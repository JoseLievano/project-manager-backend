package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController extends DefaultController <AdminDTO, AdminMiniDTO, AdminForm, Long> {

    protected AdminController(DefaultService<AdminDTO, AdminMiniDTO, AdminForm, Long> service) {
        super(service);
    }

}
