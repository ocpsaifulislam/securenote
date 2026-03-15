package com.ostad.securenote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique Note ID

    @Column(nullable = false)
    private String title; // Not Null

    @Column(nullable = false)
    private String content; // Not Null

    @Column(nullable = false)
    private String ownerUsername; // For ownership checks
}
