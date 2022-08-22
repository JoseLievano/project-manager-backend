package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrDocsCategoryServiceImplements extends DefaultServiceImplements <bsPrDocsCategoryDTO, bsPrDocsCategoryMiniDTO, bsPrDocsCategoryForm, bsPrDocsCategoryEntity, Long> {

    @Autowired
    private bsProjectRepository projectRepository;

    @Autowired
    private bsPrDocsCategoryRepository bsPrDocsCategoryRepository;

    public bsPrDocsCategoryServiceImplements(bsPrDocsCategoryRepository repository, bsPrDocsCategoryMapper mapper){
        super(repository, mapper);
    }

    @Override
    public bsPrDocsCategoryMiniDTO insert(bsPrDocsCategoryForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getProject() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generate the entity to insert from form
        bsPrDocsCategoryEntity toInsert = mapper.toEntity(form);

        //Check if the project exists
        bsProjectEntity project = projectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundExeption("Project not found"));
        toInsert.setProject(project);
        project.getDocsCategories().add(toInsert);

        //Check if the category already exists
        if (bsPrDocsCategoryRepository.existsByNameAndProject(form.getName(), project)){
            throw new ElementAlreadyExist("Category already exists");
        }

        //Save the category
        repository.save(toInsert);

        //Save the changes in project
        projectRepository.save(project);

        return mapper.toSmallDTO(toInsert);

    }
}
