package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_channel")
public class bsPrChannelController extends DefaultController <bsPrChannelDTO, bsPrChannelMiniDTO, bsPrChannelListDTO, bsPrChannelForm, Long> {

    public bsPrChannelController (bsPrChannelServiceImplements service){
        super(service);
    }

}
