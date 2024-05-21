package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table (name = "bs_pr_comment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column (length = 2000)
    private String commentContent;

    @Column
    private Date commentDate;

    @ManyToOne (optional = false)
    @JoinColumn (name = "author_id", nullable = false)
    private User author;

    @ManyToOne (optional = false)
    @JoinColumn ( name = "channel_id", nullable = false)
    private bsPrChannelEntity channel;

    @ManyToOne (optional = false)
    @JoinColumn ( name = "project_id", nullable = false)
    private bsProjectEntity project;

    @OneToMany (mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<bsPrMentionEntity> mentions;

    @Column
    private Long mentionCount;

}
