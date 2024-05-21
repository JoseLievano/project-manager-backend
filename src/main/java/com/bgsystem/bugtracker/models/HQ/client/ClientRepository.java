package com.bgsystem.bugtracker.models.HQ.client;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ClientRepository extends DefaultRepository<ClientEntity, Long> {

    Set<ClientEntity> findByUsername (String username);

    Set<ClientEntity> findByEmail (String email);

}
