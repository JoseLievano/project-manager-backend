package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Set;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bsPrChannelForm {

    private Long id;

    private String name;

    private Boolean isPublic;

    private Date creationDate;

    private String description;

    private Long author;

    private Long project;

    private Set<Long> members;

}
