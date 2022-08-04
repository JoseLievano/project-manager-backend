package com.bgsystem.bugtracker.models.client.bsType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsTypeRepository extends JpaRepository<bsTypeEntity, Long> {

    Set<bsTypeEntity> findByName (String name);

}
