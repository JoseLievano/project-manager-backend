package com.bgsystem.bugtracker.models.client.project.bsPrKBCategory;

import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "bs_pr_kb_category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrKBCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private bsProjectEntity project;

}
