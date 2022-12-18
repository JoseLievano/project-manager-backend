package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsClientRepository extends DefaultRepository<bsClientEntity, Long> {

    Set<bsClientEntity> findByUsername (String username);

    Set<bsClientEntity> findByEmail (String email);

}
