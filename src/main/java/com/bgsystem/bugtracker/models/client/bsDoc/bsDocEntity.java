package com.bgsystem.bugtracker.models.client.bsDoc;

import com.bgsystem.bugtracker.models.client.bsDocsCategory.bsDocsCategoryEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "bs_docs")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class bsDocEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column (unique = true)
    private String title;

    @Column (length = 10000)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name ="bs_doc_category_id", nullable = false)
    private bsDocsCategoryEntity bsDocsCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

}
