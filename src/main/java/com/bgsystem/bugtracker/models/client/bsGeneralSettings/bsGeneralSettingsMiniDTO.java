package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsGeneralSettingsMiniDTO {

    private Long id;

    private String logoUrl;

    private String address;

    private String website;

    private String email;

}
