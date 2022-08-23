package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bsPrDocsRepository extends JpaRepository <bsPrDocsEntity, Long>{

    Boolean existsByTitleAndCategory(String title, bsPrDocsCategoryEntity category);

}
