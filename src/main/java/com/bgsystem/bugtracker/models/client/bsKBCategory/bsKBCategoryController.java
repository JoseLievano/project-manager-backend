package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_kb_category")
public class bsKBCategoryController extends DefaultController<bsKBCategoryDTO, bsKBCategoryMiniDTO, bsKBCategoryListDTO, bsKBCategoryForm, Long> {

    public bsKBCategoryController(bsKBCategoryServiceImplements service){
        super(service);
    }

}
