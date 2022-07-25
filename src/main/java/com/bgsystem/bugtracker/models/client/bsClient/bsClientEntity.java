package com.bgsystem.bugtracker.models.client.bsClient;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Table
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

    @OneToMany(mappedBy = "bsClientEntity", orphanRemoval = true)
    private Set<BusinessEntity> businessEntities = new LinkedHashSet<>();

    @Builder(builderMethodName = "bsClientBuilder")
    public bsClientEntity(Long id,
                          String firstName,
                          String lastName,
                          String email,
                          Set<String> roles,
                          String username,
                          String password,
                          Boolean isActive,
                          Date dateCreated,
                          Date lastLogin,
                          Set<BusinessEntity> businessEntities) {
        super(id, firstName, lastName, email, roles, username, password);
    }


}
