package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsTypeRepository extends DefaultRepository<bsTypeEntity, Long> {

    Set<bsTypeEntity> findByName (String name);

}
