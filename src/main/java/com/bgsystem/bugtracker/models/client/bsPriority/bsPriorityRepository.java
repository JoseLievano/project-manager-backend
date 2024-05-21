package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public interface bsPriorityRepository extends DefaultRepository<bsPriorityEntity, Long> {

    Set<bsPriorityEntity> findByName (String name);

    ArrayList<bsPriorityEntity> findByPriorityOrderGreaterThan(Integer priorityOrderToDelete);

}
