package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryRepository;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPrDocsServiceImplements extends DefaultServiceImplements <bsPrDocsDTO, bsPrDocsMiniDTO, bsPrDocsListDTO, bsPrDocsForm, bsPrDocsEntity, Long> {

    private final bsPrDocsCategoryRepository bsPrDocsCategoryRepository;

    private final bsProjectRepository bsProjectRepository;

    private final bsPrDocsRepository docsRepository;

    private final bsPrDocsPredicate bsPrDocsPredicate;
    @Autowired
    public bsPrDocsServiceImplements (
                                    bsPrDocsRepository repository,
                                    bsPrDocsMapper mapper,
                                    bsPrDocsCategoryRepository bsPrDocsCategoryRepository,
                                    bsProjectRepository bsProjectRepository,
                                    bsPrDocsRepository docsRepository,
                                    bsPrDocsPredicate bsPrDocsPredicate
    ){
        super(repository, mapper, bsPrDocsPredicate);
        this.bsPrDocsCategoryRepository = bsPrDocsCategoryRepository;
        this.bsProjectRepository = bsProjectRepository;
        this.docsRepository = docsRepository;
        this.bsPrDocsPredicate = bsPrDocsPredicate;
    }

    @Override
    public bsPrDocsMiniDTO insert(bsPrDocsForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getTitle() == null || form.getCategory() == null || form.getProject() == null || form.getContent() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Generate the entity from the form
        bsPrDocsEntity toInsert = mapper.toEntity(form);

        //Check if the category exists
        bsPrDocsCategoryEntity category = bsPrDocsCategoryRepository.findById(form.getCategory()).orElseThrow(() -> new ElementNotFoundException("Category not found"));
        category.getDocs().add(toInsert);
        toInsert.setCategory(category);

        //Check if the project exists
        bsProjectEntity project =  bsProjectRepository.findById(form.getProject()).orElseThrow(() -> new ElementNotFoundException("Project not found"));
        project.getDocs().add(toInsert);
        toInsert.setProject(project);

        //Check if the doc already exists, with the same title and category
        if(docsRepository.existsByTitleAndCategory(form.getTitle(), category)){
            throw new ElementAlreadyExist("Document already exists");
        }

        repository.save(toInsert);

        bsProjectRepository.save(project);
        bsPrDocsCategoryRepository.save(category);

        return mapper.toSmallDTO(toInsert);

    }
}
