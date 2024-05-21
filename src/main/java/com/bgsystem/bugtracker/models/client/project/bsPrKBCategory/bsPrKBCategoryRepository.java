package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPrKBCategoryRepository extends DefaultRepository<bsPrKBCategoryEntity, Long> {

    Boolean existsByNameAndProject(String name, bsProjectEntity project);

}
