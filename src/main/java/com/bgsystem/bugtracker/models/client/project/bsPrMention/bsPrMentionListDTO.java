package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentMiniDTO;
import com.bgsystem.bugtracker.shared.models.user.UserMiniDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrMentionListDTO {

    private Long id;

    private Date mentionDate;

    private bsPrCommentMiniDTO comment;

    private UserMiniDTO author;

    private UserMiniDTO mentionedUser;

}
