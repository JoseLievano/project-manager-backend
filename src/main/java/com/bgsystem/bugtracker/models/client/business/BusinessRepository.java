package com.bgsystem.bugtracker.models.client.business;


import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessRepository extends DefaultRepository<BusinessEntity, Long> {

    Set<BusinessEntity> findByName (String name);

}
