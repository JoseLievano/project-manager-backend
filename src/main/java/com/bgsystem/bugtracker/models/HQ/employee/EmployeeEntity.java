package com.bgsystem.bugtracker.models.HQ.employee;

import com.bgsystem.bugtracker.models.HQ.mainHQ.MainHQEntity;
import com.bgsystem.bugtracker.models.HQ.plan.PlanEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table (name = "employee")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "main_hq_entity_id")
    private MainHQEntity mainHQEntity;

    @Builder(builderMethodName = "employeeBuilder")
    public EmployeeEntity(Long id,
                          String firstName,
                          String lastName,
                          String email,
                          Set<String> roles,
                          String username,
                          String password,
                          Set<bsPrChannelEntity> channels,
                          Set<bsPrChannelEntity> channelsAuthor,
                          Set<bsPrCommentEntity> comments,
                          MainHQEntity mainHQEntity){
        super(id, firstName, lastName, email, roles, username, password, channels, channelsAuthor, comments);
        this.mainHQEntity = mainHQEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlanEntity that = (PlanEntity) o;
        return id != null && Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
