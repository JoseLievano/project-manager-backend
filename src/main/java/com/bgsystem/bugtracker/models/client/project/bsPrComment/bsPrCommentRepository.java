package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface bsPrCommentRepository extends DefaultRepository<bsPrCommentEntity, Long> {

    Page<bsPrCommentEntity> findByChannel (Pageable pageable, bsPrChannelEntity channel);

}
