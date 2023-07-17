package com.bgsystem.bugtracker.models.HQ.admin;

import com.bgsystem.bugtracker.models.client.project.bsPrChannel.bsPrChannelEntity;
import com.bgsystem.bugtracker.models.client.project.bsPrComment.bsPrCommentEntity;
import com.bgsystem.bugtracker.shared.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table (name ="admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminEntity extends User {

    @Builder(builderMethodName = "adminBuilder")
    public AdminEntity (
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<String> roles,
        String username,
        String password,
        Set<bsPrChannelEntity> channels,
        Set<bsPrChannelEntity> channelsAuthor,
        Set<bsPrCommentEntity> comments
    ){
        super(id, firstName, lastName, email, roles, username, password, channels, channelsAuthor, comments);
    }

}
