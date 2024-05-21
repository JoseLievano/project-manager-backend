package com.bgsystem.bugtracker.models.client.project.bsPrDocs;

import com.bgsystem.bugtracker.models.client.project.bsPrDocsCategory.bsPrDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "bs_pr_docs")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsPrDocsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column (length = 10000)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bs_pr_doc_category_id", nullable = false)
    private bsPrDocsCategoryEntity category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private bsProjectEntity project;

}
