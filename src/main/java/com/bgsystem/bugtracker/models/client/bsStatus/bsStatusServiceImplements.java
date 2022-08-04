package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsStatusServiceImplements extends DefaultServiceImplements <bsStatusDTO, bsStatusMiniDTO, bsStatusForm, bsStatusEntity, Long> {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private bsStatusRepository bsStatusRepository;

    public bsStatusServiceImplements(bsStatusRepository repository, bsStatusMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public bsStatusMiniDTO insert(bsStatusForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getName() == null || form.getColor() == null) {
            throw new InvalidInsertDeails("Invalid insert details, can´t create new status");
        }

        //Check if a status with the same namd or color already exists
        if (bsStatusRepository.findByName(form.getName()).size() > 0 || bsStatusRepository.findByColor(form.getColor()).size() > 0) {
            throw new ElementAlreadyExist("Status already exists with the same name or color");
        }

        bsStatusEntity toInsert = mapper.toEntity(form);

        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));

        toInsert.setBusiness(business);

        business.getBsStatusEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}
