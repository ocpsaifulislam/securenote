package com.ostad.securenote.service;

import com.ostad.securenote.entity.Note;
import com.ostad.securenote.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note createNote(Note note, String username) {
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUser(String username) {
        return noteRepository.findByOwnerUsername(username);
    }

    public Optional<Note> getNoteByIdAndUser(Long id, String username) {
        return noteRepository.findById(id)
                .filter(note -> note.getOwnerUsername().equals(username));
    }

    public void deleteNoteForUser(Long id, String username) {
        getNoteByIdAndUser(id, username).ifPresent(noteRepository::delete);
    }

    // Admin Operations
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void adminDeleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
