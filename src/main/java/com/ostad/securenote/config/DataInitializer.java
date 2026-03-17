package com.ostad.securenote.config;

import com.ostad.securenote.entity.User;
import com.ostad.securenote.entity.Note;
import com.ostad.securenote.repository.NoteRepository;
import com.ostad.securenote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserRepository  userRepository;
    private final NoteRepository  noteRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {

            // ── Users ──────────────────────────────────────────────────────────

            User alice = userRepository.save(User.builder()
                    .username("alice")
                    .password(passwordEncoder.encode("alice123"))
                    .role("ROLE_USER")
                    .build());

            User bob = userRepository.save(User.builder()
                    .username("bob")
                    .password(passwordEncoder.encode("bob123"))
                    .role("ROLE_USER")
                    .build());

            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ROLE_ADMIN")
                    .build());

            // ── Notes ──────────────────────────────────────────────────────────

            noteRepository.save(Note.builder()
                    .title("Alice's Shopping List")
                    .content("Milk, eggs, bread, butter")
                    .ownerUsername(alice.getUsername())
                    .build());

            noteRepository.save(Note.builder()
                    .title("Alice's Meeting Notes")
                    .content("Discuss Q4 roadmap with the team on Friday.")
                    .ownerUsername(alice.getUsername())
                    .build());

            noteRepository.save(Note.builder()
                    .title("Bob's Workout Plan")
                    .content("Monday: chest. Wednesday: back. Friday: legs.")
                    .ownerUsername(bob.getUsername())
                    .build());

            noteRepository.save(Note.builder()
                    .title("Bob's Book List")
                    .content("Clean Code, The Pragmatic Programmer, Designing Data-Intensive Applications")
                    .ownerUsername(bob.getUsername())
                    .build());

            log.info("==============================================");
            log.info("  Seed data loaded successfully!");
            log.info("  Users  : alice (USER), bob (USER), admin (ADMIN)");
            log.info("  Notes  : 2 for alice, 2 for bob");
            log.info("  H2 console : http://localhost:8084/h2-console");
            log.info("  JDBC URL   : jdbc:h2:mem:securenotedb");
            log.info("==============================================");
        };
    }
}
