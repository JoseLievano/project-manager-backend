package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsGeneralSettingsRepository extends DefaultRepository<bsGeneralSettingsEntity, Long> {
}
