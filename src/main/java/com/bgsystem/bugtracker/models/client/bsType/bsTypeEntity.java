package com.bgsystem.bugtracker.models.client.bsType;

import lombok.*;

import javax.persistence.*;

@Table (name = "bs_type")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTypeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

}
