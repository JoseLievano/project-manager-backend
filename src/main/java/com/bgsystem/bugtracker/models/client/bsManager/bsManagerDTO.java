package com.bgsystem.bugtracker.models.client.bsManager;

import com.bgsystem.bugtracker.models.client.business.BusinessMiniDTO;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class bsManagerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;

    private String username;

    private Date dateCreated;

    private Date lastLogin;

    private BusinessMiniDTO business;

}
