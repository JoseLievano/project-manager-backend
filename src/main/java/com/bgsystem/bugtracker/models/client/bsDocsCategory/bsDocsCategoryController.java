package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_docs_category")
public class bsDocsCategoryController extends DefaultController<bsDocsCategoryDTO, bsDocsCategoryMiniDTO, bsDocsCategoryForm, Long> {

    public bsDocsCategoryController (bsDocsCategoryServiceImplements service){
        super(service);
    }

}
