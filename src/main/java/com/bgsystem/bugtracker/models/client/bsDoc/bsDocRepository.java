package com.bgsystem.bugtracker.models.client.bsDoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsDocRepository extends JpaRepository <bsDocEntity, Long> {

    Set<bsDocEntity> findByTitle(String title);

}
