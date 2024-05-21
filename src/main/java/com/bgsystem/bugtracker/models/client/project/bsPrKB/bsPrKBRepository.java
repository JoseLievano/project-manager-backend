package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrKBRepository extends DefaultRepository<bsPrKBEntity, Long> {

    Boolean existsByTitleAndCategory (String title, bsPrKBCategoryEntity category);

}
