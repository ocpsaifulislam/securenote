package dev.shoaibsuad.securenotetrack.repository;

import dev.shoaibsuad.securenotetrack.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByOwnerUsername(String ownerUsername);

    Optional<Note> findByIdAndOwnerUsername(Integer id, String ownerUsername);
}
