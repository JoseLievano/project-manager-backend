package com.bgsystem.bugtracker.shared.models.geo.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class countryForm {

    private Long id;

    @NonNull
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
