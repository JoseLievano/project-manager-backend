package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsGeneralSettingsRepository extends JpaRepository<bsGeneralSettingsEntity, Long> {
}
