package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQRepository;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeServiceImplements extends DefaultServiceImplements<EmployeeDTO, EmployeeMiniDTO, EmployeeListDTO, EmployeeForm, EmployeeEntity, Long> {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final MainHQRepository mainHQRepository;

    private final EmployeePredicate employeePredicate;

    @Autowired
    public EmployeeServiceImplements(
                                    EmployeeRepository repository,
                                    EmployeeMapper mapper,
                                    PasswordEncoder passwordEncoder,
                                    MainHQRepository mainHQRepository,
                                    EmployeePredicate employeePredicate
    ) {
        super(repository, mapper, employeePredicate);
        this.employeeRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mainHQRepository = mainHQRepository;
        this.employeePredicate = employeePredicate;
    }

    @Override
public EmployeeMiniDTO insert(EmployeeForm employeeForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        if (employeeForm == null || employeeForm.getPassword() == null || employeeForm.getEmail() == null) {
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new employee");
        }

        //Check if the employee already exist in our DB
        Set<EmployeeEntity> employeeExistenceCheck = employeeRepository.findByUsername(employeeForm.getUsername());

        employeeExistenceCheck.addAll(employeeRepository.findByEmail(employeeForm.getEmail()));

        if (employeeExistenceCheck.size() > 0) {
            throw new ElementAlreadyExist();
        }

        //Encode the password
        employeeForm.setPassword(passwordEncoder.encode(employeeForm.getPassword()));

        EmployeeEntity toInsert = mapper.toEntity(employeeForm);

        //Set the role
        Set<String> roles = Set.of("ROLE_EMPLOYEE");

        toInsert.setRoles(roles);

        //Insert the MainHQ in the employee
        MainHQEntity mainHQEntity = mainHQRepository.findAll().get(0);

        if (mainHQEntity == null) {
            throw new ElementNotFoundException("The MainHQ is not found in our DB");
        }else {
            toInsert.setMainHQEntity(mainHQEntity);

            mainHQEntity.getEmployeeEntities().add(toInsert);
        }

        //Save the employee in the DB
        employeeRepository.save(toInsert);

        mainHQRepository.save(mainHQEntity);

        return mapper.toSmallDTO(toInsert);
    }

    @Override
    public EmployeeDTO delete(Long id) throws ElementNotFoundException {

        EmployeeEntity toDelete = employeeRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("The employee is not found"));

        EmployeeDTO employeeDTO = mapper.toDTO(toDelete);

        employeeDTO.setRoles(toDelete.getRoles());

        //Bug: is necessary to sout the roles of the employee to return the correct DTO
        employeeDTO.getRoles().forEach(role -> System.out.println("EmployeeServiceImplements delete() - role: " + role));

        repository.delete(toDelete);

        return employeeDTO;

    }
}
