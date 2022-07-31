package com.bgsystem.bugtracker.models.client.bsManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsManagerRepository extends JpaRepository<bsManagerEntity, Long> {

    Set<bsManagerEntity> findByUsername (String username);

    Set<bsManagerEntity> findByEmail (String email);

}
