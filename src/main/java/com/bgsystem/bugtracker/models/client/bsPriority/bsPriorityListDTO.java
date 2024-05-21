package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPriorityListDTO {

    private Long id;

    private String name;

    private Integer priorityOrder;

    private BusinessMiniDTO business;

    private Long tasks;

}
