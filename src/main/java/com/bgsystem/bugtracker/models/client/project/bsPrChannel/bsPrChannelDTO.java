package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectMiniDTO;
import com.bgsystem.bugtracker.shared.models.user.UserMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrChannelDTO {

    private Long id;

    private String name;

    private Boolean isPublic;

    private Date creationDate;

    private String description;

    private UserMiniDTO author;

    private bsProjectMiniDTO project;

    private Set<UserMiniDTO> members;

    private Set<bsPrCommentMiniDTO> comments;

}
