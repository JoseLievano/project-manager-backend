package com.bgsystem.bugtracker.models.client.project.bsPrTask;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bs_pr_tasks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date created;

    @Column
    private Date dueDate;

    @Column
    private Boolean isInternal;

    @Column
    private Boolean isOverDue;

    @Column
    private Boolean isDone;

}
