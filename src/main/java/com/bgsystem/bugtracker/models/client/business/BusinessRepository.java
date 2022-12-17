package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long>, QuerydslPredicateExecutor<BusinessEntity> {

    Set<BusinessEntity> findByName (String name);

    Page<BusinessEntity> findAll(Pageable pageable);

    Set<BusinessEntity> findAllByClient (ClientEntity clientEntity);

    Set<BusinessEntity> findAllByPlan (PlanEntity planEntity);

    Page<BusinessEntity> findByClient (Pageable pageable, ClientEntity clientEntity);

    Page<BusinessEntity> findByPlan (Pageable pageable, PlanEntity planEntity);

}
