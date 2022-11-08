package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsGeneralSettingsListDTO {

    private Long id;

    private String logoUrl;

    private String address;

    private String website;

    private String email;

    private BusinessMiniDTO business;

}
