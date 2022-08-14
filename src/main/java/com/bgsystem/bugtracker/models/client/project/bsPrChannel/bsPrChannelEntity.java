package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table (name = "bs_pr_channel")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isPublic;

    @ManyToOne (optional = false)
    @JoinColumn (name = "author_id", nullable = false)
    private User author;

    @ManyToOne (optional = false)
    @JoinColumn ( name = "project_id", nullable = false)
    private bsProjectEntity project;

    @OneToMany (mappedBy = "channel")
    private Set<bsPrCommentEntity> comments;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "channel_and_members_relations",
            joinColumns =@JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<User> members;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "channel_and_task_relations",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<bsPrTaskEntity> tasks = new LinkedHashSet<>();

}
