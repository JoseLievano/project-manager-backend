package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.models.client.business.BusinessEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table (name = "bs_manager")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class bsManagerEntity extends User {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Date dateCreated;

    @Column
    private Date lastLogin;

    @ManyToOne (optional = false)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity business;

    @Builder(builderMethodName = "bsManagerBuilder")
    public bsManagerEntity(Long id,
                           String firstName,
                           String lastName,
                           String email,
                           Set<String> roles,
                           String username,
                           String password,
                           Date dateCreated,
                           Date lastLogin,
                           BusinessEntity business) {
        super(id, firstName, lastName, email, roles, username, password);
    }
}
