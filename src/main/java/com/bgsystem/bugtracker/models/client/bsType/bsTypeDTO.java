package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTypeDTO {

    private Long id;

    private String name;

    private BusinessMiniDTO business;

}
