package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsTaskCategoryRepository extends DefaultRepository<bsTaskCategoryEntity, Long> {

    Set<bsTaskCategoryEntity> findByNameAndBusiness (String name, BusinessEntity business);

}
