package com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory;

import com.bgsystem.bugtracker.models.client.project.bsPrDocs.bsPrDocsEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "bs_pr_docs_category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrDocsCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private bsProjectEntity project;

    @OneToMany(mappedBy = "category")
    private Set<bsPrDocsEntity> docs;

    @Column
    private Long docsCount;

}
