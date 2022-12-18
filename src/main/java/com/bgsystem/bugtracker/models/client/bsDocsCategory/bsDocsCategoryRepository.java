package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsDocsCategoryRepository extends DefaultRepository<bsDocsCategoryEntity, Long> {

    Set<bsDocsCategoryEntity> findByName(String name);

}
