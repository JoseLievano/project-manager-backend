package com.bgsystem.bugtracker.models.client.bsFile;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsFileDTO {

    private Long id;

    private String url;

    private String name;

    private String type;

    private BusinessMiniDTO business;

}
