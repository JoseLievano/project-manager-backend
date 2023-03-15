package com.bgsystem.bugtracker.models.client.bsStatus;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "bs_status")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsStatusEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String color;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private Set<bsPrTaskEntity> tasks;

    @Column
    private Long taskCount;

}
