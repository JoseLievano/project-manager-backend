package com.bgsystem.bugtracker.shared.models.user;

import com.bgsystem.bugtracker.exeptions.ElementAlreadyExist;
import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.exeptions.InvalidInsertDeails;
import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImplements extends DefaultServiceImplements <UserDTO, UserMiniDTO, UserListDTO, UserForm, User, Long> {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPredicate userPredicate;

    public UserServiceImplements(UserRepository repository, UserMapper mapper, UserPredicate userPredicate) {
        super(repository, mapper, userPredicate);
    }

    @Override
    public UserMiniDTO insert(UserForm userForm) throws ElementNotFoundException, ElementAlreadyExist, InvalidInsertDeails {

        //Check if the form is valid
        String invalidDetailsMessage = "The form is not complete, is not possible to register a new user";

        if (userForm == null || userForm.getPassword() == null || userForm.getEmail() == null || userForm.getRoles().size() == 0){
            throw new InvalidInsertDeails(invalidDetailsMessage);
        }

        //Check if the user already exist in our DB
        Set<User> userExistenceCheck = userRepository.findByUsername(userForm.getUsername());

        userExistenceCheck.addAll(userRepository.findByEmail(userForm.getEmail()));

        if (userExistenceCheck.size() > 0){
            throw new ElementAlreadyExist();
        }

        userForm.setPassword(encoder.encode(userForm.getPassword()));

        User toInsert = mapper.toEntity(userForm);

        repository.save(toInsert);

        return mapper.toSmallDTO(toInsert);

    }
}
