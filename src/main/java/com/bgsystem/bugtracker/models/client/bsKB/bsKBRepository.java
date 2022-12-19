package com.bgsystem.bugtracker.models.client.bsKB;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsKBRepository extends DefaultRepository<bsKBEntity, Long> {

    Set <bsKBEntity> findByTitle (String title);

    Set <bsKBEntity> findByTitleAndBusiness (String title, BusinessEntity business);

}
