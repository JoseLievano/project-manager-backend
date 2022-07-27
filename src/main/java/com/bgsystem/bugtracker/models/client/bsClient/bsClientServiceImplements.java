package com.bgsystem.bugtracker.models.client.bsClient;

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
public class bsClientServiceImplements extends DefaultServiceImplements<bsClientDTO, bsClientMiniDTO, bsClientForm, bsClientEntity, Long> {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private bsClientRepository bsClientRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public bsClientServiceImplements(bsClientRepository repository, bsClientMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public bsClientMiniDTO insert(bsClientForm bsClientForm) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (bsClientForm == null || bsClientForm.getPassword() == null || bsClientForm.getEmail() == null || bsClientForm.getBusiness() == null) {
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new client");
        }

        //Check if the user already exist in our DB
        Set<bsClientEntity> userExistenceCheck = bsClientRepository.findByUsername(bsClientForm.getUsername());

        userExistenceCheck.addAll(bsClientRepository.findByEmail(bsClientForm.getEmail()));

        if (userExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist();
        }

        //Encode the password inside the form
        bsClientForm.setPassword(encoder.encode(bsClientForm.getPassword()));

        //Converts form to an Admin Entity
        bsClientEntity toInsert = mapper.toEntity(bsClientForm);

        //Set the role
        Set<String> roles = Set.of("ROLE_BS_CLIENT");
        toInsert.setRoles(roles);

        //Insert the Business
        BusinessEntity business = businessRepository.findById(bsClientForm.getBusiness()).orElseThrow(ElementNotFoundExeption::new);
        toInsert.setBusiness(business);

        //Insert the client into the business
        business.setBsClientEntities(Set.of(toInsert));
        businessRepository.save(business);

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
