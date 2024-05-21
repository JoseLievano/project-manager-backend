package com.bgsystem.bugtracker.models.client.bsKB;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBMiniDTO {

    private Long id;

    private String title;

    private String content;

}
