package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsDocServiceImplements extends DefaultServiceImplements <bsDocDTO, bsDocMiniDTO, bsDocForm, bsDocEntity, Long> {

    private final bsDocRepository bsDocRepository;

    private final BusinessRepository businessRepository;

    private final bsDocsCategoryRepository bsDocsCategoryRepository;

    @Autowired
    public bsDocServiceImplements(bsDocRepository repository, bsDocMapper mapper, bsDocRepository bsDocRepository, BusinessRepository businessRepository, bsDocsCategoryRepository bsDocsCategoryRepository) {
        super(repository, mapper);
        this.bsDocRepository = bsDocRepository;
        this.businessRepository = businessRepository;
        this.bsDocsCategoryRepository = bsDocsCategoryRepository;
    }

    @Override
    public bsDocMiniDTO insert(bsDocForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getTitle() == null || form.getBsDocsCategory() == null || form.getContent() == null)
            throw new InvalidInsertDeails("Invalid insert details");

        //Check if the doc already exists
        if (bsDocRepository.findByTitle(form.getTitle()).size() > 0)
            throw new ElementAlreadyExist("Doc already exists");

        //Get the entity to insert
        bsDocEntity toInsert = mapper.toEntity(form);

        //Get the business entity
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));
        toInsert.setBusiness(business);

        //Add the doc to the business
        business.getBsDocEntities().add(toInsert);

        //Get the category entity
        bsDocsCategoryEntity category = bsDocsCategoryRepository.findById(form.getBsDocsCategory()).orElseThrow(() -> new ElementNotFoundExeption("Category not found"));
        toInsert.setBsDocsCategory(category);

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
