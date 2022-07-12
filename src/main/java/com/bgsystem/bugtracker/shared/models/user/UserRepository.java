package com.bgsystem.bugtracker.shared.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    //Custom JPA query to implement inside SecurityUserServiceImplementation
    Set<User> findByUsername(String username);

    //Find user with email
    Set<User> findByEmail(String email);
}
