package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryEntity;
import com.bgsystem.bugtracker.models.client.bsKBCategory.bsKBCategoryRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsKBServiceImplements extends DefaultServiceImplements <bsKBDTO, bsKBMiniDTO, bsKBListDTO, bsKBForm, bsKBEntity, Long> {

    private final bsKBRepository bsKBRepository;

    private final BusinessRepository businessRepository;

    private final bsKBCategoryRepository bsKBCategoryRepository;

    @Autowired
    public bsKBServiceImplements(
        bsKBRepository repository,
        bsKBMapper mapper,
        bsKBRepository bsKBRepository,
        BusinessRepository businessRepository,
        bsKBCategoryRepository bsKBCategoryRepository,
        bsKBPredicate bsKBPredicate
    ) {
        super(repository, mapper, bsKBPredicate);
        this.bsKBRepository = bsKBRepository;
        this.businessRepository = businessRepository;
        this.bsKBCategoryRepository = bsKBCategoryRepository;
    }

    @Override
    public bsKBMiniDTO insert(bsKBForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getCategory() == null || form.getContent() == null || form.getTitle() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        //Check if the doc already exists
        if (bsKBRepository.findByTitle(form.getTitle()).size() > 0)
            throw new ElementAlreadyExist("KB aldready exist");

        //Get the entity to insert
        bsKBEntity toInsert = mapper.toEntity(form);

        //Get entity's category
        bsKBCategoryEntity category = bsKBCategoryRepository.findById(form.getCategory()).orElseThrow(ElementNotFoundException::new);
        toInsert.setBsKBCategory(category);

        //Get entity's business from category
        BusinessEntity business = category.getBusiness();
        toInsert.setBusiness(business);

        //Add doc to business
        business.getBsKBs().add(toInsert);

        //Add kb to category
        category.getBsKBEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);
        bsKBCategoryRepository.save(category);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    public bsKBDTO update(Long id, bsKBForm form) throws ElementNotFoundException, InvalidInsertDeails {

        if (form == null)
            throw new InvalidInsertDeails("Invalid insert details");

        bsKBEntity newEntityData = mapper.toEntity(form);

        //Get the entity we are going to modify
        bsKBEntity toUpdate = repository.findById(id).orElseThrow(ElementNotFoundException::new);

        if (form.getTitle() != null){
            toUpdate.setTitle(newEntityData.getTitle());
        }

        if (form.getContent() != null){
            toUpdate.setContent(newEntityData.getContent());
        }

        if (form.getCategory() != null){
            bsKBCategoryEntity category = bsKBCategoryRepository.findById(form.getCategory()).orElseThrow(ElementNotFoundException::new);
            toUpdate.setBsKBCategory(category);
            repository.save(toUpdate);
            bsKBCategoryRepository.save(category);
        }else {
            repository.save(toUpdate);
        }

        return mapper.toDTO(toUpdate);

    }
}
