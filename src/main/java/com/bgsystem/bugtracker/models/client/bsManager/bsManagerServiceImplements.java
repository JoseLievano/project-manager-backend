package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class bsManagerServiceImplements extends DefaultServiceImplements <bsManagerDTO, bsManagerMiniDTO, bsManagerForm, bsManagerEntity, Long> {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private bsManagerRepository bsManagerRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public bsManagerServiceImplements(bsManagerRepository repository, bsManagerMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public bsManagerMiniDTO insert(bsManagerForm form) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (form == null || form.getPassword() == null || form.getEmail() == null || form.getUsername() == null) {
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new manager");
        }

        //Check if the manager already exist in our DB
        Set<bsManagerEntity> userExistenceCheck = bsManagerRepository.findByUsername(form.getUsername());
        userExistenceCheck.addAll(bsManagerRepository.findByEmail(form.getEmail()));

        if (userExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist("The manager already exist in our DB");
        }

        //Encode the password inside the form
        form.setPassword(encoder.encode(form.getPassword()));

        //Converts form to an Admin Entity
        bsManagerEntity toInsert = mapper.toEntity(form);

        //Set the role
        Set<String> roles = Set.of("ROLE_BS_MANAGER");
        toInsert.setRoles(roles);

        //Insert the Business
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(ElementNotFoundExeption::new);

        toInsert.setBusiness(business);

        //Insert the manager into the business
        business.getBsManagerEntities().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}