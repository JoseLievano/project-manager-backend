package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bs_pr_mentions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrMentionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Date mentionDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    private bsPrCommentEntity comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mentioned_user_id", nullable = false)
    private User mentionedUser;

}
