package com.bgsystem.bugtracker.models.HQ.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Set<EmployeeEntity> findByUsername(String username);

    Set<EmployeeEntity> findByEmail(String email);

}
