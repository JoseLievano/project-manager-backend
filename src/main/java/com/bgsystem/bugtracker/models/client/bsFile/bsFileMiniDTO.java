package com.bgsystem.bugtracker.models.client.bsFile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsFileMiniDTO {

    private Long id;

    private String name;

    private String type;

}
