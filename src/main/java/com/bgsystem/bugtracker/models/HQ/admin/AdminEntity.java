package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table (name ="admin")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity extends User {

    @Builder(builderMethodName = "adminBuilder")
    public AdminEntity (Long id, String firstName, String lastName, String email, Set<String> roles, String username, String password){
        super(id, firstName, lastName, email, roles, username, password);
    }

}
