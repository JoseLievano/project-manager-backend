package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class bsManagerServiceImplements extends DefaultServiceImplements <bsManagerDTO, bsManagerMiniDTO, bsManagerListDTO, bsManagerForm, bsManagerEntity, Long> {

    private final PasswordEncoder encoder;

    private final bsManagerRepository bsManagerRepository;

    private final BusinessRepository businessRepository;

    private final bsManagerPredicate bsManagerPredicate;
    @Autowired
    public bsManagerServiceImplements(
                                    bsManagerRepository repository,
                                    bsManagerMapper mapper,
                                    PasswordEncoder encoder,
                                    BusinessRepository businessRepository,
                                    bsManagerRepository bsManagerRepository,
                                    bsManagerPredicate bsManagerPredicate
    ) {
        super(repository, mapper, bsManagerPredicate);
        this.encoder = encoder;
        this.businessRepository = businessRepository;
        this.bsManagerRepository = bsManagerRepository;
        this.bsManagerPredicate = bsManagerPredicate;
    }

    @Override
    public bsManagerMiniDTO insert(bsManagerForm form) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

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
        BusinessEntity business = businessRepository.findById(form.getBusiness()).orElseThrow(ElementNotFoundException::new);

        toInsert.setBusiness(business);

        //Insert the manager into the business
        business.getBsManagers().add(toInsert);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);

    }
}
