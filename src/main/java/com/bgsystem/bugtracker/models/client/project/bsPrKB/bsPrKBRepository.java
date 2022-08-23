package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrKBRepository extends JpaRepository <bsPrKBEntity, Long> {

    Boolean existsByTitleAndCategory (String title, bsPrKBCategoryEntity category);

}
