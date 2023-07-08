package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsStatusListDTO {

    private Long id;

    private String name;

    private String color;

    private BusinessMiniDTO business;

    private Long tasks;

}
