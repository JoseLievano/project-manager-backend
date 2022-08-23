package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPrKBCategoryRepository extends JpaRepository <bsPrKBCategoryEntity, Long> {

    Boolean existsByNameAndProject(String name, bsProjectEntity project);

}
