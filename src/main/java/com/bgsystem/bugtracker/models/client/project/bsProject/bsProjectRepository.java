package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsProjectRepository extends JpaRepository<bsProjectEntity, Long> {

    Set<bsProjectEntity> findByNameAndBusinessAndClient (String name, BusinessEntity business, bsClientEntity client);

}
