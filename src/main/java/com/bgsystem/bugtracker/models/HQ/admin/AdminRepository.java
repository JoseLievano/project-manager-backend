package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AdminRepository extends DefaultRepository<AdminEntity, Long> {

    Set<AdminEntity> findByUsername (String username);

    Set<AdminEntity> findByEmail (String email);

}
