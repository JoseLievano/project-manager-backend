package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bsPrDocsRepository extends DefaultRepository<bsPrDocsEntity, Long> {

    Boolean existsByTitleAndCategory(String title, bsPrDocsCategoryEntity category);

}
