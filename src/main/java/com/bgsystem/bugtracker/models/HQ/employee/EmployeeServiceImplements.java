package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class EmployeeServiceImplements extends DefaultServiceImplements<EmployeeDTO, EmployeeMiniDTO, EmployeeForm, EmployeeEntity, Long> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImplements(EmployeeRepository repository, EmployeeMapper mapper) {
        super(repository, mapper);
    }

    @Override
public EmployeeMiniDTO insert(EmployeeForm employeeForm) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

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

        //Save the employee in the DB
        employeeRepository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
