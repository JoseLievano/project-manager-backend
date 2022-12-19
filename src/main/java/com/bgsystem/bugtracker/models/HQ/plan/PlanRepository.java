package com.bgsystem.bugtracker.models.HQ.plan;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PlanRepository extends DefaultRepository<PlanEntity, Long> {

    Set<PlanEntity> findByName (String name);

}
