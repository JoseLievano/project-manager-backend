package com.bgsystem.bugtracker.models.client.bsStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsStatusRepository extends JpaRepository<bsStatusEntity, Long> {

}
