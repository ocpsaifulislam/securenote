package dev.shoaibsuad.securenotetrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "notes")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @SequenceGenerator(
            name = "note_seq",
            sequenceName = "note_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
    @Column(updatable = false)
    private Integer id;

    @NotBlank(message = "Title must not be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Content must not be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank
    @Column(nullable = false)
    private String ownerUsername;
}
