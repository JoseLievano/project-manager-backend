package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsKBCategoryServiceImplements extends DefaultServiceImplements <bsKBCategoryDTO, bsKBCategoryMiniDTO, bsKBCategoryForm, bsKBCategoryEntity, Long> {

    private final bsKBCategoryRepository bsKBCategoryRepository;

    private final BusinessRepository businessRepository;

    @Autowired
    public bsKBCategoryServiceImplements(bsKBCategoryRepository repository, bsKBCategoryMapper mapper, bsKBCategoryRepository bsKBCategoryRepository, BusinessRepository businessRepository) {
        super(repository, mapper);
        this.bsKBCategoryRepository = bsKBCategoryRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public bsKBCategoryMiniDTO insert(bsKBCategoryForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getName() == null || form.getBusiness() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Check if there is a KB Category with the same name for the same business
        if (bsKBCategoryRepository.findByNameAndBusinessId(form.getName(), form.getBusiness()).size() > 0) {
            throw new ElementAlreadyExist("KB Category with the same name for the same business already exist");
        }

        bsKBCategoryEntity toInsert = mapper.toEntity(form);

        //Add the business to the kb Category
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));
        toInsert.setBusiness(business);

        business.getBsKBCategoryEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}
