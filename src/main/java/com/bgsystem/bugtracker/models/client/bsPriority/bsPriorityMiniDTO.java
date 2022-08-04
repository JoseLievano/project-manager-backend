package com.bgsystem.bugtracker.models.client.bsPriority;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPriorityMiniDTO {

    private Long id;

    private String name;

    private Integer priorityOrder;

}
