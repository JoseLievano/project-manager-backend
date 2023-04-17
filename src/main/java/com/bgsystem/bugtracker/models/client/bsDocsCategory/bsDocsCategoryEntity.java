package com.bgsystem.bugtracker.models.client.bsDocsCategory;

import com.bgsystem.bugtracker.models.client.bsDoc.bsDocEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "bs_docs_category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsDocsCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean isAParentCategory;

    @Column
    private Long level;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private bsDocsCategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory", orphanRemoval = true)
    private Set<bsDocsCategoryEntity> subCategories;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @OneToMany(mappedBy = "bsDocsCategory" , orphanRemoval = true)
    private Set<bsDocEntity> bsDocs;

    @Column
    private Long subCategoriesCount;

    @Column
    private Long bsDocsCount;

}
