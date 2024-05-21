package com.bgsystem.bugtracker.models.client.bsStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsStatusMiniDTO {

    private Long id;

    private String name;

    private String color;

}
