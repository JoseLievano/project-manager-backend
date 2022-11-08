package com.bgsystem.bugtracker.shared.models.user;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends DefaultController <UserDTO, UserMiniDTO, UserListDTO, UserForm, Long> {

    public UserController(DefaultService<UserDTO, UserMiniDTO, UserListDTO, UserForm, Long> service){
        super(service);
    }
}
