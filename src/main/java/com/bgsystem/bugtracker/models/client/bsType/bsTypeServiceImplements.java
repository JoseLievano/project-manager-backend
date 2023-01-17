package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsTypeServiceImplements extends DefaultServiceImplements <bsTypeDTO, bsTypeMiniDTO, bsTypeListDTO, bsTypeForm, bsTypeEntity, Long> {

    private final bsTypeRepository bsTypeRepository;

    private final BusinessRepository businessRepository;

    private final bsTypePredicate bsTypePredicate;
    @Autowired
    public bsTypeServiceImplements(
                                bsTypeRepository repository,
                                bsTypeMapper mapper,
                                bsTypeRepository bsTypeRepository,
                                BusinessRepository businessRepository,
                                bsTypePredicate bsTypePredicate
    ) {
        super(repository, mapper, bsTypePredicate);
        this.bsTypeRepository = bsTypeRepository;
        this.businessRepository = businessRepository;
        this.bsTypePredicate = bsTypePredicate;
    }

    @Override
    public bsTypeMiniDTO insert(bsTypeForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if( form == null || form.getBusiness() == null || form.getName() == null){
            throw new InvalidInsertDeails("Invalid insert details, can´t create new type");
        }

        //Check if a type with the same name already exists
        if(bsTypeRepository.findByName(form.getName()).size() > 0){
            throw new ElementAlreadyExist("Type already exists with the same name");
        }

        bsTypeEntity toInsert = mapper.toEntity(form);

        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        toInsert.setBusiness(business);

        business.getBsTypes().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }

    @Override
    protected bsTypeEntity updateListFields(bsTypeEntity bsTypeEntity) {

        bsTypeEntity.setTaskCount(bsTypeEntity.getTasks() != null ? 0 : (long) bsTypeEntity.getTasks().size());

        return bsTypeEntity;

    }
}
