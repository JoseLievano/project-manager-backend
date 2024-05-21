package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_pr_task")
public class bsPrTaskController extends DefaultController<bsPrTaskDTO, bsPrTaskMiniDTO, bsPrTaskListDTO, bsPrTaskForm, Long> {

    public bsPrTaskController(bsPrTaskServiceImplements service){
        super(service);
    }

}
