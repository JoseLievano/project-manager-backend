package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrCommentRepository extends JpaRepository<bsPrCommentEntity, Long> {

}
