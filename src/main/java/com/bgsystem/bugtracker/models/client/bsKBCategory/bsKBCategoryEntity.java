package com.bgsystem.bugtracker.models.client.bsKBCategory;

import com.bgsystem.bugtracker.models.client.bsKB.bsKBEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "bs_kb_category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsKBCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean isAParentKBCategory;

    @Column
    private Long level;

    @ManyToOne
    @JoinColumn(name = "parent_kb_id")
    private bsKBCategoryEntity parentKB;

    @OneToMany(mappedBy = "parentKB", orphanRemoval = true)
    private Set<bsKBCategoryEntity> subKBCategories;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @OneToMany(mappedBy = "bsKBCategory", orphanRemoval = true)
    private Set<bsKBEntity> bsKBEntities = new HashSet<>();

    private Long subKBCategoriesCount;

    private Long bsKBCount;

}
