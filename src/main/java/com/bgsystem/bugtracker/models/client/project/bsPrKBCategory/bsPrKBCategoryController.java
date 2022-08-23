package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_kb_category")
public class bsPrKBCategoryController extends DefaultController <bsPrKBCategoryDTO, bsPrKBCategoryMiniDTO, bsPrKBCategoryForm, Long> {

    public bsPrKBCategoryController (bsPrKBCategoryServiceImplements service){
        super(service);
    }

}
