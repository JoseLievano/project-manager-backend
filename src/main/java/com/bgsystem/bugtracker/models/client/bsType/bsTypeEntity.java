package com.bgsystem.bugtracker.models.client.bsType;

import com.bgsystem.bugtracker.models.client.bsTaskCategory.bsTaskCategoryEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table (name = "bs_type")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTypeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "types")
    private Set<bsTaskCategoryEntity> taskCategories;

    @Column
    private Long taskCategoriesCount;

    @OneToMany(mappedBy = "type")
    private Set<bsPrTaskEntity> tasks;

    @Column
    private Long taskCount;

}
