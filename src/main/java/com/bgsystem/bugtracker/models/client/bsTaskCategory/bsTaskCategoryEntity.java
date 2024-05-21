package com.bgsystem.bugtracker.models.client.bsTaskCategory;

import com.bgsystem.bugtracker.models.client.bsType.bsTypeEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrTask.bsPrTaskEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "bs_task_categories")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsTaskCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_category_and_type_relations",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<bsTypeEntity> types;

    @Column
    private Long typesCount;

    @OneToMany(mappedBy = "category")
    private Set<bsPrTaskEntity> tasks;

    @Column
    private Long taskCount;

}
