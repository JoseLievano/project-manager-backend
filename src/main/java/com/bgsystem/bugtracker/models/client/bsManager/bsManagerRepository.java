package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsManagerRepository extends DefaultRepository<bsManagerEntity, Long> {

    Set<bsManagerEntity> findByUsername (String username);

    Set<bsManagerEntity> findByEmail (String email);

}
