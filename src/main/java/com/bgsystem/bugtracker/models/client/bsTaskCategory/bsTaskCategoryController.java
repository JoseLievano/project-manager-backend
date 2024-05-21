package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bs_task_category")
public class bsTaskCategoryController extends DefaultController <bsTaskCategoryDTO, bsTaskCategoryMiniDTO, bsTaskCategoryListDTO, bsTaskCategoryForm, Long> {

    private final bsTaskCategoryServiceImplements bsTaskCategoryRepository;

    public bsTaskCategoryController (bsTaskCategoryServiceImplements service){
        super(service);
        this.bsTaskCategoryRepository = service;
    }

    @GetMapping("/is-empty")
    public ResponseEntity<Boolean> taskCatIsEmpty(){
        return ResponseEntity.ok(bsTaskCategoryRepository.taskCatIsEmpty());
    }
}
