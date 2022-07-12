package com.bgsystem.bugtracker.models.client.business;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessMiniDTO {

    private Long id;

    private String name;

    private String taxID;

}
