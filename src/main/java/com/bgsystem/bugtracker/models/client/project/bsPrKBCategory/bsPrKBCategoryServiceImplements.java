package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrKBCategoryServiceImplements extends DefaultServiceImplements <bsPrKBCategoryDTO, bsPrKBCategoryMiniDTO, bsPrKBCategoryForm, bsPrKBCategoryEntity, Long> {

    @Autowired
    private bsPrKBCategoryRepository bsPrKBCategoryRepository;

    @Autowired
    private bsProjectRepository bsProjectRepository;

    public bsPrKBCategoryServiceImplements (bsPrKBCategoryRepository repository, bsPrKBCategoryMapper mapper){
        super(repository, mapper);
    }

    @Override
    public bsPrKBCategoryMiniDTO insert(bsPrKBCategoryForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getProject() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generates the entity from the form
        bsPrKBCategoryEntity toInsert = mapper.toEntity(form);

        //Checks if the project exists, and if exists then add it to the project
        bsProjectEntity project = bsProjectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundExeption("Project not found"));
        toInsert.setProject(project);
        project.getKbCategories().add(toInsert);

        //Checks if the KB category already exists
        if (bsPrKBCategoryRepository.existsByNameAndProject(form.getName(), project)) {
            throw new ElementAlreadyExist("KB category already exists");
        }

        //Save the Category entity
        repository.save(toInsert);

        bsProjectRepository.save(project);

        return mapper.toSmallDTO(toInsert);

    }
}
