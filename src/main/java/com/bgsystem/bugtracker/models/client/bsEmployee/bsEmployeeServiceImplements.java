package com.bgsystem.bugtracker.models.client.bsEmployee;

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
public class bsEmployeeServiceImplements extends DefaultServiceImplements<bsEmployeeDTO, bsEmployeeMiniDTO, bsEmployeeListDTO, bsEmployeeForm, bsEmployeeEntity, Long> {

    private final PasswordEncoder passwordEncoder;

    private final bsEmployeeRepository bsEmployeeRepository;

    private final BusinessRepository businessRepository;

    private final bsEmployeePredicate bsEmployeePredicate;
    @Autowired
    public bsEmployeeServiceImplements(
                                        bsEmployeeRepository repository,
                                        bsEmployeeMapper mapper,
                                        PasswordEncoder passwordEncoder,
                                        bsEmployeeRepository bsEmployeeRepository,
                                        BusinessRepository businessRepository,
                                        bsEmployeePredicate bsEmployeePredicate
    ) {
        super(repository, mapper, bsEmployeePredicate);
        this.passwordEncoder = passwordEncoder;
        this.bsEmployeeRepository = bsEmployeeRepository;
        this.businessRepository = businessRepository;
        this.bsEmployeePredicate = bsEmployeePredicate;
    }

    @Override
    public bsEmployeeMiniDTO insert(bsEmployeeForm bsEmployeeForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (bsEmployeeForm == null || bsEmployeeForm.getBusiness() == null || bsEmployeeForm.getEmail() == null || bsEmployeeForm.getUsername() == null)
            throw new InvalidInsertDeails("Invalid insert details");

        //check if the employee already exists in our DB
        Set<bsEmployeeEntity> userExistenceCheck = bsEmployeeRepository.findByUsername(bsEmployeeForm.getUsername());
        userExistenceCheck.addAll(bsEmployeeRepository.findByEmail(bsEmployeeForm.getEmail()));

        if (userExistenceCheck.size() > 0)
            throw new ElementAlreadyExist("Manager already exists");

        //encode the password inside the form
        bsEmployeeForm.setPassword(passwordEncoder.encode(bsEmployeeForm.getPassword()));

        //converts form to a bsEmployee Entity
        bsEmployeeEntity toInsert = mapper.toEntity(bsEmployeeForm);

        //set the role
        Set<String> roles = Set.of("ROLE_BS_EMPLOYEE");
        toInsert.setRoles(roles);

        //Insert the business
        BusinessEntity business = businessRepository.findById(bsEmployeeForm.getBusiness()).orElseThrow(ElementNotFoundException::new);

        toInsert.setBusiness(business);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);
    }
}
