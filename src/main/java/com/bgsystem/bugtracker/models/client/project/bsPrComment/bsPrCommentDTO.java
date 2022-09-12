package com.bgsystem.bugtracker.models.client.project.bsPrComment;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelMiniDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionDTO;
import com.bgsystem.bugtracker.models.client.project.bsPrMention.bsPrMentionMiniDTO;
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
public class bsPrCommentDTO {

    private Long id;

    private String commentContent;

    private Date commentDate;

    private UserMiniDTO author;

    private bsPrChannelMiniDTO channel;

    private bsProjectMiniDTO project;

    private Set<bsPrMentionDTO> mentions;

}
