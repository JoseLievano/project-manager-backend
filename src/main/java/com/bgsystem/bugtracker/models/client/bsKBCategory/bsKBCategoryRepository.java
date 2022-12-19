package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsKBCategoryRepository extends DefaultRepository<bsKBCategoryEntity, Long> {

    Set<bsKBCategoryEntity> findByName (String name);

    Set<bsKBCategoryEntity> findByNameAndBusinessId (String name, Long businessId);
}
