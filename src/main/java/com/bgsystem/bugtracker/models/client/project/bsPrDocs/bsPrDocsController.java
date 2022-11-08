package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_docs")
public class bsPrDocsController extends DefaultController <bsPrDocsDTO, bsPrDocsMiniDTO, bsPrDocsListDTO, bsPrDocsForm, Long> {

    public bsPrDocsController (bsPrDocsServiceImplements service){
        super(service);
    }

}
