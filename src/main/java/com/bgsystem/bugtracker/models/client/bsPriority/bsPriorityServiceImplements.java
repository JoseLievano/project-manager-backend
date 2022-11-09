package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsPriorityServiceImplements extends DefaultServiceImplements <bsPriorityDTO, bsPriorityMiniDTO, bsPriorityListDTO, bsPriorityForm, bsPriorityEntity, Long> {

    private final BusinessRepository businessRepository;

    private final bsPriorityRepository priorityRepository;

    @Autowired
    public bsPriorityServiceImplements( bsPriorityRepository repository, bsPriorityMapper mapper, BusinessRepository businessRepository, bsPriorityRepository priorityRepository) {
        super( repository, mapper );
        this.businessRepository = businessRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public bsPriorityMiniDTO insert(bsPriorityForm bsPriorityForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if ( bsPriorityForm == null || bsPriorityForm.getBusiness() == null || bsPriorityForm.getName() == null ) {
            throw new InvalidInsertDeails("Invalid insert details, can´t create new priority");
        }

        //Check if a priority with the same name already exists
        if ( priorityRepository.findByName(bsPriorityForm.getName()).size() > 0 ) {
            throw new ElementAlreadyExist("Priority already exists");
        }

        bsPriorityEntity toInsert = mapper.toEntity(bsPriorityForm);

        System.out.println("business id: " + bsPriorityForm.getBusiness());

        //Get the business
        BusinessEntity business = businessRepository.findById(bsPriorityForm.getBusiness()).orElseThrow(() -> new ElementNotFoundException("Business not found"));

        //Add the business to the priority
        toInsert.setBusiness(business);

        //Add the new priority to the business
        business.getBsPriorityEntities().add(toInsert);

        //Save the business
        businessRepository.save(business);

        //Save the priority
        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
