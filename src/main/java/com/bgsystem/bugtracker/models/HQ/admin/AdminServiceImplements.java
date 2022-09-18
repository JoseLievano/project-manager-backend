package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminServiceImplements extends DefaultServiceImplements <AdminDTO, AdminMiniDTO, AdminForm, AdminEntity, Long>{

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdminRepository adminRepository;

    public AdminServiceImplements(AdminRepository repository, AdminMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public AdminMiniDTO insert(AdminForm userForm) throws ElementNotFoundExeption, ElementAlreadyExist, InvalidInsertDeails {

        if (userForm == null || userForm.getPassword() == null || userForm.getEmail() == null){
            throw new InvalidInsertDeails("The form is not complete, is not possible to register a new admin");
        }

        //Check if the user already exist in our DB
        Set<AdminEntity> userExistenceCheck = adminRepository.findByUsername(userForm.getUsername());

        userExistenceCheck.addAll(adminRepository.findByEmail(userForm.getEmail()));

        if (userExistenceCheck.size() > 0){
            throw new ElementAlreadyExist();
        }

        //Encode the password inside the form
        userForm.setPassword(encoder.encode(userForm.getPassword()));

        //Converts form to an Admin Entity
        AdminEntity toInsert = mapper.toEntity(userForm);
        //Set the role
        Set<String> roles = Set.of("ROLE_ADMIN");
        toInsert.setRoles(roles);

        repository.save(toInsert);
        return mapper.toSmallDTO(toInsert);
    }

    @Override
    public AdminDTO delete(Long id) throws ElementNotFoundExeption {

        AdminEntity toDelete = repository.findById(id).orElseThrow(() -> new ElementNotFoundExeption("The admin is not found"));

        AdminDTO adminDTO = mapper.toDTO(toDelete);

        adminDTO.setRoles(toDelete.getRoles());

        //Bug: is necessary to sout the adminDTO roles to return the correct DTO
        adminDTO.getRoles().forEach(role -> System.out.println("AdminServiceImplements delete() -> " + role));

        repository.delete(toDelete);

        return adminDTO;

    }
}
