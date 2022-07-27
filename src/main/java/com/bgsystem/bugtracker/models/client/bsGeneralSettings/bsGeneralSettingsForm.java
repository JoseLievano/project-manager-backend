package com.bgsystem.bugtracker.models.client.bsGeneralSettings;

import lombok.*;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsGeneralSettingsForm {

    private Long id;

    private String logoUrl;

    private String address;

    private String website;

    private String email;

    private Long business;

}
