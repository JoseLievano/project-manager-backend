package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long> {

    Set<BusinessEntity> findByName (String name);

    Set<BusinessEntity> findAllByClientEntity (ClientEntity clientEntity);

    Set<BusinessEntity> findAllByPlanEntity (PlanEntity planEntity);

    Page<BusinessEntity> findByClientEntity (Pageable pageable, ClientEntity clientEntity);

    Page<BusinessEntity> findByPlanEntity (Pageable pageable, PlanEntity planEntity);

}
