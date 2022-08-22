package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrDocsCategoryRepository extends JpaRepository <bsPrDocsCategoryEntity, Long> {

    Boolean existsByNameAndProject (String name, bsProjectEntity project);

}
