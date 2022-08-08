package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.bsClient.bsClientRepository;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bsProjectServiceImplements extends DefaultServiceImplements <bsProjectDTO, bsProjectMiniDTO, bsProjectForm, bsProjectEntity, Long> {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private bsClientRepository bsClientRepository;

    @Autowired
    private bsProjectRepository bsProjectRepository;

    public bsProjectServiceImplements (bsProjectRepository repository, bsProjectMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public bsProjectMiniDTO insert(bsProjectForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getBusiness() == null || form.getName() == null || form.getClient() == null) {
            throw new InvalidInsertDeails("Invalid insert details");
        }

        bsProjectEntity toInsert = mapper.toEntity(form);

        //Check if business exists
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(() -> new ElementNotFoundExeption("Business not found"));

        //Check if client exists
        bsClientEntity client = bsClientRepository.findById(form.getClient()).orElseThrow(() -> new ElementNotFoundExeption("Client not found"));

        //Check if a project with the same name, business and cliente already exists
        if (bsProjectRepository.findByNameAndBusinessAndClient(form.getName(), business, client).size() > 0) {
            throw new ElementAlreadyExist("Project already exists");
        }

        //Link the business to the project
        business.getBsProjectEntities().add(toInsert);
        toInsert.setBusiness(business);

        //Link the client to the project
        client.getProjects().add(toInsert);
        toInsert.setClient(client);

        repository.save(toInsert);

        businessRepository.save(business);

        bsClientRepository.save(client);

        return mapper.toSmallDTO(toInsert);

    }
}