package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsDocsCategoryServiceImplements extends DefaultServiceImplements <bsDocsCategoryDTO, bsDocsCategoryMiniDTO, bsDocsCategoryForm, bsDocsCategoryEntity, Long> {

    private final bsDocsCategoryRepository bsDocsCategoryRepository;

    private final BusinessRepository businessRepository;

    @Autowired
    public bsDocsCategoryServiceImplements(bsDocsCategoryRepository repository, bsDocsCategoryMapper mapper, bsDocsCategoryRepository bsDocsCategoryRepository, BusinessRepository businessRepository) {
        super(repository, mapper);
        this.bsDocsCategoryRepository = bsDocsCategoryRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public bsDocsCategoryMiniDTO insert(bsDocsCategoryForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getName() == null)
            throw new InvalidInsertDeails("Invalid insert details");

        //Check if the category already exists
        if (bsDocsCategoryRepository.findByName(form.getName()).size() > 0)
            throw new ElementAlreadyExist("Category already exists");

        bsDocsCategoryEntity toInsert = mapper.toEntity(form);

        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));

        toInsert.setBusiness(business);

        business.getBsDocsCategoryEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);
    }
}
