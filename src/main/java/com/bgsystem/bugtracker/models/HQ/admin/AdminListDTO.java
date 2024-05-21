package com.bgsystem.bugtracker.models.HQ.admin;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdminListDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

}
