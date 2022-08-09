package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.models.client.bsClient.bsClientEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bs_projects")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isCompleted;

    @Column
    private Date created;

    @Column
    private Date dueDate;

    //Todo: Add an Owner field to the project entity

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_client_id", nullable = false)
    private bsClientEntity client;

}
