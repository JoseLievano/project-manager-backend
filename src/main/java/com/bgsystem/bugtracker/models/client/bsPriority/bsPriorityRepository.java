package com.bgsystem.bugtracker.models.client.bsPriority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPriorityRepository extends JpaRepository<bsPriorityEntity, Long> {

    Set<bsPriorityEntity> findByName (String name);

}