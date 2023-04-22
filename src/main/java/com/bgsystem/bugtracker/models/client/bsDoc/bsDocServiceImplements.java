package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsDocServiceImplements extends DefaultServiceImplements <bsDocDTO, bsDocMiniDTO, bsDocListDTO, bsDocForm, bsDocEntity, Long> {

    private final bsDocRepository bsDocRepository;

    private final BusinessRepository businessRepository;

    private final bsDocsCategoryRepository bsDocsCategoryRepository;

    private final bsDocPredicate bsDocPredicate;

    @Autowired
    public bsDocServiceImplements(
                                bsDocRepository repository,
                                bsDocMapper mapper,
                                bsDocRepository bsDocRepository,
                                BusinessRepository businessRepository,
                                bsDocsCategoryRepository bsDocsCategoryRepository,
                                bsDocPredicate bsDocPredicate
    ) {
        super(repository, mapper, bsDocPredicate);
        this.bsDocRepository = bsDocRepository;
        this.businessRepository = businessRepository;
        this.bsDocsCategoryRepository = bsDocsCategoryRepository;
        this.bsDocPredicate = bsDocPredicate;
    }

    @Override
    public bsDocMiniDTO insert(bsDocForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getTitle() == null || form.getBsDocsCategory() == null || form.getContent() == null)
            throw new InvalidInsertDeails("Invalid insert details");

        //Check if the doc already exists
        if (bsDocRepository.findByTitle(form.getTitle()).size() > 0)
            throw new ElementAlreadyExist("Doc already exists");

        //Get the entity to insert
        bsDocEntity toInsert = mapper.toEntity(form);

        //Get the category entity
        bsDocsCategoryEntity category = bsDocsCategoryRepository.findById(form.getBsDocsCategory()).orElseThrow(() -> new ElementNotFoundException("Category not found"));
        toInsert.setBsDocsCategory(category);

        //Get the business entity from category
        BusinessEntity business = category.getBusiness();
        toInsert.setBusiness(business);

        //Add the doc to the business
        business.getBsDocs().add(toInsert);

        //Add the doc to the category
        category.getBsDocs().add(toInsert);

        //Save the entity
        repository.save(toInsert);

        //Persiste changes inside business and category
        businessRepository.save(business);
        bsDocsCategoryRepository.save(category);

        return mapper.toSmallDTO(toInsert);

    }
}
