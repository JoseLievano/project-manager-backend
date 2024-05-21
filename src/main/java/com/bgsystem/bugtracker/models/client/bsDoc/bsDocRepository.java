package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsDocRepository extends DefaultRepository<bsDocEntity, Long> {

    Set<bsDocEntity> findByTitle(String title);

}
