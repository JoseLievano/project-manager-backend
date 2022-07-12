package com.bgsystem.bugtracker.models.HQ.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Set<AdminEntity> findByUsername (String username);

    Set<AdminEntity> findByEmail (String email);

}
