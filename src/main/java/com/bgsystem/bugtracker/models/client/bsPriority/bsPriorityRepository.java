package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPriorityRepository extends DefaultRepository<bsPriorityEntity, Long> {

    Set<bsPriorityEntity> findByName (String name);

}
