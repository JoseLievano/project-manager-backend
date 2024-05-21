package com.bgsystem.bugtracker.models.client.project.bsPrChannel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bsPrChannelMiniDTO {

    private Long id;

    private String name;

    private Boolean isPublic;

    private Date creationDate;

    private String description;

}
