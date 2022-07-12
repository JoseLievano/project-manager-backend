package com.bgsystem.bugtracker.models.HQ.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PlanRepository extends JpaRepository <PlanEntity, Long>{

    Set<PlanEntity> findByName (String name);

}
