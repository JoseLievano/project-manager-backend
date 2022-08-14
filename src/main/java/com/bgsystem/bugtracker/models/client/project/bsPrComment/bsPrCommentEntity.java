package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne (optional = false)
    @JoinColumn (name = "author_id", nullable = false)
    private User author;

    @ManyToOne (optional = false)
    @JoinColumn ( name = "channel_id", nullable = false)
    private bsPrChannelEntity channel;

    @ManyToOne (optional = false)
    @JoinColumn ( name = "project_id", nullable = false)
    private bsProjectEntity project;

}
