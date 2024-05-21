package com.bgsystem.bugtracker.models.client.bsFile;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "bs_file")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column (unique = true, length = 1000)
    private String url;

    @Column
    private String name;

    @Column
    private String type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity businessEntity;

}
