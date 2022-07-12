package com.bgsystem.bugtracker.models.HQ.mainHQ;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainHQ")
public class MainHQController extends DefaultController<MainHQDTO, MainHQMiniDTO, MainHQForm, Long> {

    protected MainHQController(DefaultService<MainHQDTO, MainHQMiniDTO, MainHQForm, Long> service) {
        super(service);
    }

}
