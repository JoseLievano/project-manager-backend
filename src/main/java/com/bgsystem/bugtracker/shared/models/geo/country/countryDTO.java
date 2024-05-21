package com.bgsystem.bugtracker.shared.models.geo.country;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class countryDTO {

    private Long id;

    private String name;

    private String code;

    private Long phone;

    private String symbol;

    private String capital;

    private String currency;

    private String continent;

    private String continentCode;

    private String alpha3;

}
