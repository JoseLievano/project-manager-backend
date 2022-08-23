package com.bgsystem.bugtracker.models.client.project.bsPrMention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrMentionForm {

    private Long id;

    private Date mentionDate;

    private Long comment;

    private Long author;

    private Long mentionedUser;

}
