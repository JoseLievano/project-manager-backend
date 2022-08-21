package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_comment")
public class bsPrCommentController extends DefaultController <bsPrCommentDTO, bsPrCommentMiniDTO, bsPrCommentForm, Long>{

    public bsPrCommentController (bsPrCommentServiceImplements service){
        super(service);
    }

}
