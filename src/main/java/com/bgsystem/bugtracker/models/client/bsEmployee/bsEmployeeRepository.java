package com.bgsystem.bugtracker.models.client.bsEmployee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsEmployeeRepository extends JpaRepository<bsEmployeeEntity, Long> {

    Set<bsEmployeeEntity> findByUsername(String username);

    Set<bsEmployeeEntity> findByEmail(String email);

}

