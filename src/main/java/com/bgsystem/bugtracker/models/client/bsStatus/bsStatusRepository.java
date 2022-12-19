package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsStatusRepository extends DefaultRepository<bsStatusEntity, Long> {

    Set<bsStatusEntity> findByName (String name);

    Set<bsStatusEntity> findByColor (String color);

}
