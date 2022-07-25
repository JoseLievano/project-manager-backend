package com.bgsystem.bugtracker.models.client.bsClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsClientRepository extends JpaRepository<bsClientEntity, Long> {

    Set<bsClientEntity> findByUsername (String username);

    Set<bsClientEntity> findByEmail (String email);

}
