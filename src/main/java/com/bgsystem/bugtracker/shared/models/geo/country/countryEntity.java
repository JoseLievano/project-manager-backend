package com.bgsystem.bugtracker.shared.models.geo.country;

import lombok.*;

import javax.persistence.*;

@Table(name = "country")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class countryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Long phone;

    @Column
    private String symbol;

    @Column
    private String capital;

    @Column
    private String currency;

    @Column
    private String continent;

    @Column
    private String continentCode;

    @Column
    private String alpha3;

}
