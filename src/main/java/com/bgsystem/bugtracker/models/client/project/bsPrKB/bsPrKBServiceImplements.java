package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryRepository;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBServiceImplements extends DefaultServiceImplements <bsPrKBDTO, bsPrKBMiniDTO, bsPrKBForm, bsPrKBEntity, Long> {

    private final bsPrKBRepository bsPrKBRepository;

    private final bsProjectRepository bsProjectRepository;

    private final bsPrKBCategoryRepository bsPrKBCategoryRepository;

    @Autowired
    public bsPrKBServiceImplements (bsPrKBRepository repository,
                                    bsPrKBMapper mapper,
                                    bsPrKBRepository bsPrKBRepository,
                                    bsProjectRepository bsProjectRepository,
                                    bsPrKBCategoryRepository bsPrKBCategoryRepository)
    {
        super (repository, mapper);
        this.bsPrKBRepository = bsPrKBRepository;
        this.bsProjectRepository = bsProjectRepository;
        this.bsPrKBCategoryRepository = bsPrKBCategoryRepository;
    }

    @Override
    public bsPrKBMiniDTO insert(bsPrKBForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getProject() == null || form.getCategory() == null || form.getTitle() == null || form.getContent() == null){
            throw new InvalidInsertDeails ("Invalid insert details");
        }

        //Get the entity from the form
        bsPrKBEntity toInsert = mapper.toEntity(form);

        //Check if the project exists, if exist then add the KB to the project
        bsProjectEntity project = bsProjectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundExeption ("Project not found"));
        toInsert.setProject(project);
        project.getKbs().add(toInsert);

        //Check if the category exists, if exist then add the KB to the category
        bsPrKBCategoryEntity category = bsPrKBCategoryRepository.findById(form.getCategory()).orElseThrow(() -> new ElementNotFoundExeption ("Category not found"));
        toInsert.setCategory(category);
        category.getKbs().add(toInsert);

        //Check if the KB already exists
        if (bsPrKBRepository.existsByTitleAndCategory(form.getTitle(), category)){
            throw new ElementAlreadyExist ("KB already exists");
        }

        //Save the entity
        repository.save(toInsert);

        //Save the KB and Project
        bsProjectRepository.save(project);
        bsPrKBCategoryRepository.save(category);

        //Return the MiniDTO
        return mapper.toSmallDTO(toInsert);

    }
}
