package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long>, JpaSpecificationExecutor<BusinessEntity> {

    Set<BusinessEntity> findByName (String name);

    Set<BusinessEntity> findAllByClient (ClientEntity clientEntity);

    Set<BusinessEntity> findAllByPlan (PlanEntity planEntity);

    Page<BusinessEntity> findByClient (Pageable pageable, ClientEntity clientEntity);

    Page<BusinessEntity> findByPlan (Pageable pageable, PlanEntity planEntity);

}
