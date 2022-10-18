package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrCommentRepository extends JpaRepository<bsPrCommentEntity, Long> {

    Page<bsPrCommentEntity> findByChannelOrderByCommentDateDesc (Pageable pageable, bsPrChannelEntity channel);

}
