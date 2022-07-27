package com.bgsystem.bugtracker.models.client.bsGeneralSettings;


import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;

@Table (name = "bs_general_settings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsGeneralSettingsEntity {

    @Id
    @Column
    private Long id;

    @Column
    private String logoUrl;

    @Column
    private String address;

    @Column
    private String website;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false, orphanRemoval = true)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

}
