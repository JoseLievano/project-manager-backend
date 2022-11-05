package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.models.HQ.client.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long> {

    Set<BusinessEntity> findByName (String name);

    Set<BusinessEntity> findAllByClientEntity (ClientEntity clientEntity);

    Page<BusinessEntity> findByClientEntity (Pageable pageable, ClientEntity clientEntity);

}
