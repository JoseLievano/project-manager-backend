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

    private final bsKBPredicate bsKBPredicate;
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
        this.bsKBPredicate = bsKBPredicate;
    }

    @Override
    public bsKBMiniDTO insert(bsKBForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getBsKBCategory() == null || form.getContent() == null || form.getTitle() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        // Check if business exists
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));
        // Check if bsKBCategory exists
        bsKBCategoryEntity bsKBCategory = bsKBCategoryRepository.findById(form.getBsKBCategory()).orElseThrow(() -> new ElementNotFoundException("bsKBCategory not found"));

        //Check if a KB with the same title and the same business already exists
        if (bsKBRepository.findByTitleAndBusiness(form.getTitle(), business).size() > 0) {
            throw new ElementAlreadyExist("KB with the same title and the same business already exists");
        }

        bsKBEntity toInsert = mapper.toEntity(form);

        toInsert.setBusiness(business);

        toInsert.setBsKBCategory(bsKBCategory);

        business.getBsKBs().add(toInsert);

        bsKBCategory.getBsKBEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        bsKBCategoryRepository.save(bsKBCategory);

        return mapper.toSmallDTO(toInsert);

    }
}
