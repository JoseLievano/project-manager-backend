package com.bgsystem.bugtracker.models.HQ.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientRepository extends JpaRepository <ClientEntity, Long> {

    Set<ClientEntity> findByUsername (String username);

    Set<ClientEntity> findByEmail (String email);

}
