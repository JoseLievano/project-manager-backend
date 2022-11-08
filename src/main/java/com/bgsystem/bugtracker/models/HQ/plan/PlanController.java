package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/plan")
public class PlanController extends DefaultController<PlanDTO, PlanMiniDTO, PlanListDTO, PlanForm, Long> {

    protected PlanController(DefaultService<PlanDTO, PlanMiniDTO, PlanListDTO, PlanForm, Long> service) {
        super(service);
    }

}
