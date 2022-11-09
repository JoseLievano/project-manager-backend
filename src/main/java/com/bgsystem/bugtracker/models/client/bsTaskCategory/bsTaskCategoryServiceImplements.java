package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsTaskCategoryServiceImplements extends DefaultServiceImplements <bsTaskCategoryDTO, bsTaskCategoryMiniDTO, bsTaskCategoryListDTO, bsTaskCategoryForm, bsTaskCategoryEntity, Long> {

    private final bsTaskCategoryRepository bsTaskCategoryRepository;

    private final BusinessRepository businessRepository;

    @Autowired
    public bsTaskCategoryServiceImplements(bsTaskCategoryRepository repository, bsTaskCategoryMapper mapper, bsTaskCategoryRepository bsTaskCategoryRepository, BusinessRepository businessRepository) {
        super(repository, mapper);
        this.bsTaskCategoryRepository = bsTaskCategoryRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public bsTaskCategoryMiniDTO insert(bsTaskCategoryForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        //Validate form
        if(form == null || form.getBusiness() == null || form.getName() == null){
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Check if category with same name and business already exist
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        if (bsTaskCategoryRepository.findByNameAndBusiness(form.getName(), business).size() > 0){
            throw new ElementAlreadyExist("Category with same name and business already exist");
        }

        //Create the task category entity
        bsTaskCategoryEntity toInsert = mapper.toEntity(form);

        //Set the Business in the Task Category
        toInsert.setBusiness(business);

        //Set the task category in the business
        business.getBsTaskCategories().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}
