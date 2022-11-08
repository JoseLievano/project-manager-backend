package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_mention")
public class bsPrMentionController extends DefaultController <bsPrMentionDTO, bsPrMentionMiniDTO, bsPrMentionListDTO, bsPrMentionForm, Long> {

    public bsPrMentionController (bsPrMentionServiceImplements service){
        super(service);
    }

}
