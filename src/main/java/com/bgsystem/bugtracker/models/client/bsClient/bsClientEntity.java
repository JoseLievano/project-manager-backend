package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.models.client.bsInvoice.bsInvoiceEntity;
import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.models.client.project.bsProject.bsProjectEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Table (name = "bs_client")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class bsClientEntity extends User {

    @Column
    private Boolean isActive;

    @Column
    private Date dateCreated;

    @Column
    private Date lastLogin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set <bsProjectEntity> projects = new LinkedHashSet<>();

    @Column
    private Long projectsCount;

    @OneToMany(mappedBy = "client")
    private Set <bsInvoiceEntity> invoices;

    @Column
    private Long invoicesCount;

    @Builder(builderMethodName = "bsClientBuilder")
    public bsClientEntity(Long id,
                          String firstName,
                          String lastName,
                          String email,
                          Set<String> roles,
                          String username,
                          String password,
                          Set<bsPrChannelEntity> channels,
                          Set<bsPrChannelEntity> channelsAuthor,
                          Set<bsPrCommentEntity> comments,
                          Boolean isActive,
                          Date dateCreated,
                          Date lastLogin,
                          BusinessEntity business,
                          Set<bsProjectEntity> projects,
                          Set <bsInvoiceEntity> invoices) {
        super(id, firstName, lastName, email, roles, username, password, channels, channelsAuthor, comments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        bsClientEntity that = (bsClientEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
