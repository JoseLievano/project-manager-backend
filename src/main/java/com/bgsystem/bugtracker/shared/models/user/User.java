package com.bgsystem.bugtracker.shared.models.user;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    private String firstName;

    @Column (length = 100)
    private String lastName;

    @Column (length = 100)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @Column
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private boolean accountNonExpired = true;
    @Column(nullable = false)
    private boolean accountNonLocked = true;
    @Column(nullable = false)
    private boolean credentialsNonExpired = true;
    @Column(nullable = false)
    private boolean enabled = true;

    public User(Long id, String firstName, String lastName, String email, Set<String> roles, String username, String password) {

        this.id = id;

        this.firstName = firstName;

        this.lastName = lastName;

        this.email = email;

        this.roles = roles;

        this.username = username;

        this.password = password;

    }
}
