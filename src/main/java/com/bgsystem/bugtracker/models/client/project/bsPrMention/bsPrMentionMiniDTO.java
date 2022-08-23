package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrMentionMiniDTO {

    private Long id;

    private Date mentionDate;

}
