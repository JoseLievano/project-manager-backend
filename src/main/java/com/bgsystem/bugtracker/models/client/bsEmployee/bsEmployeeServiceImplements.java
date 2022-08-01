package com.bgsystem.bugtracker.models.client.bsEmployee;

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
public class bsEmployeeServiceImplements extends DefaultServiceImplements<bsEmployeeDTO, bsEmployeeMiniDTO, bsEmployeeForm, bsEmployeeEntity, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private bsEmployeeRepository bsEmployeeRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public bsEmployeeServiceImplements(bsEmployeeRepository repository, bsEmployeeMapper mapper){
        super(repository, mapper);
    }

    @Override
    public bsEmployeeMiniDTO insert(bsEmployeeForm bsEmployeeForm) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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
        BusinessEntity business = businessRepository.findById(bsEmployeeForm.getBusiness()).orElseThrow(ElementNotFoundExeption::new);

        toInsert.setBusiness(business);

        repository.save(toInsert);

        businessRepository.save(business);

        return mapper.toSmallDTO(toInsert);
    }
}
