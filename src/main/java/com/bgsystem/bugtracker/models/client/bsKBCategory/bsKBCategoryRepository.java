package com.bgsystem.bugtracker.models.client.bsKBCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsKBCategoryRepository extends JpaRepository <bsKBCategoryEntity, Long> {

    Set<bsKBCategoryEntity> findByName (String name);

    Set<bsKBCategoryEntity> findByNameAndBusinessId (String name, Long businessId);
}
