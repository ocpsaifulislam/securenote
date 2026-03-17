package com.ostad.securenote.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Content must not be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * Stores the username of whoever created this note.
     * Used for ownership verification.
     */
    @NotBlank
    @Column(nullable = false)
    private String ownerUsername;
}