package com.bgsystem.bugtracker.models.client.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long> {

    Set<BusinessEntity> findByName (String name);

}
