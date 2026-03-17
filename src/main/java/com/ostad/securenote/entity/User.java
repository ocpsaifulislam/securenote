package com.ostad.securenote.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    /**
     * Role must be stored with the ROLE_ prefix, e.g., "ROLE_USER" or "ROLE_ADMIN"
     */
    @NotBlank
    @Column(nullable = false)
    private String role;


}