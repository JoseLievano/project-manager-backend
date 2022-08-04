package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsTypeServiceImplements extends DefaultServiceImplements <bsTypeDTO, bsTypeMiniDTO, bsTypeForm, bsTypeEntity, Long> {

    @Autowired
    private bsTypeRepository bsTypeRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public bsTypeServiceImplements(bsTypeRepository repository, bsTypeMapper mapper){
        super(repository, mapper);
    }

    @Override
    public bsTypeMiniDTO insert(bsTypeForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if( form == null || form.getBusiness() == null || form.getName() == null){
            throw new InvalidInsertDeails("Invalid insert details, can´t create new type");
        }

        //Check if a type with the same name already exists
        if(bsTypeRepository.findByName(form.getName()).size() > 0){
            throw new ElementAlreadyExist("Type already exists with the same name");
        }

        bsTypeEntity toInsert = mapper.toEntity(form);

        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));

        toInsert.setBusiness(business);

        business.getBsTypeEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}
