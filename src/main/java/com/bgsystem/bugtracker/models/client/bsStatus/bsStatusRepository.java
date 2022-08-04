package com.bgsystem.bugtracker.models.client.bsStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsStatusRepository extends JpaRepository<bsStatusEntity, Long> {

    Set<bsStatusEntity> findByName (String name);

    Set<bsStatusEntity> findByColor (String color);

}
