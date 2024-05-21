package com.bgsystem.bugtracker.models.client.project.bsPrKB;

import com.bgsystem.bugtracker.models.client.project.bsPrKBCategory.bsPrKBCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "bs_pr_kb")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrKBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column(length = 10000)
    private String content;

    @ManyToOne (optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private bsPrKBCategoryEntity category;

    @ManyToOne (optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private bsProjectEntity project;



}
