package com.bgsystem.bugtracker.shared.models.user;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserRepository extends DefaultRepository <User, Long> {

    //Custom JPA query to implement inside SecurityUserServiceImplementation
    Set<User> findByUsername(String username);

    //Find user with email
    Set<User> findByEmail(String email);

    Boolean existsByUsername (String username);
}
