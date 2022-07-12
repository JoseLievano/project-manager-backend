package com.bgsystem.bugtracker.models.client.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BusinessRepository extends JpaRepository <BusinessEntity, Long> {
}
