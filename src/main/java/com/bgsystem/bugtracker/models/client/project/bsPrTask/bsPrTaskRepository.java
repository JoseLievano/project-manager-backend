package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPrTaskRepository extends DefaultRepository<bsPrTaskEntity, Long> {

    Set<bsPrTaskEntity> findByNameAndBusinessAndCategoryAndProject (String name, BusinessEntity business, bsTaskCategoryEntity category, bsProjectEntity project);

}
