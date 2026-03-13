package com.ostad.securenote.repository;

import com.ostad.securenote.entity.Note;
import com.ostad.securenote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerOrderByUpdatedAtDesc(User owner);

    Optional<Note> findByIdAndOwner(Long id, User owner);

    boolean existsByIdAndOwner(Long id, User owner);
}
