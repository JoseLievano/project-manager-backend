package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_project")
public class bsProjectController extends DefaultController <bsProjectDTO, bsProjectMiniDTO, bsProjectListDTO, bsProjectForm, Long> {

    public bsProjectController (bsProjectServiceImplements service){
        super (service);
    }

}
