package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bs_pr_docs_category")
public class bsPrDocsCategoryController extends DefaultController <bsPrDocsCategoryDTO, bsPrDocsCategoryMiniDTO, bsPrDocsCategoryListDTO, bsPrDocsCategoryForm, Long>{

    public bsPrDocsCategoryController (bsPrDocsCategoryServiceImplements service){
        super(service);
    }

}
