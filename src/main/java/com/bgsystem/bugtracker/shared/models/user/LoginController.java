package com.bgsystem.bugtracker.shared.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public LoginController(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping(value = {"/", ""})
    public UserMiniDTO getUserDetailsAfterLogin(Principal user) {

        Set<User> users = userRepository.findByUsername(user.getName());

        if (users.size() > 0) {
            return userMapper.toSmallDTO(users.iterator().next());
        }else {
            return null;
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
