package com.bgsystem.bugtracker.models.client.bsEmployee;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsEmployeeRepository extends DefaultRepository <bsEmployeeEntity, Long> {

    Set<bsEmployeeEntity> findByUsername(String username);

    Set<bsEmployeeEntity> findByEmail(String email);

}

