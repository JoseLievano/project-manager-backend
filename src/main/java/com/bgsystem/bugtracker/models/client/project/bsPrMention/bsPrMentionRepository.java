package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.repository.DefaultRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface bsPrMentionRepository extends DefaultRepository<bsPrMentionEntity, Long> {

    Boolean existsByCommentAndAuthorAndMentionedUser (bsPrCommentEntity comment, User author, User mentionedUser);

    Set<bsPrMentionEntity> findByCommentAndAuthorAndMentionedUser (bsPrCommentEntity comment, User author, User mentionedUser);

}
