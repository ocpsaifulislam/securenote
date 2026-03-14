package com.ostad.securenote.service;


import com.ostad.securenote.entity.Note;
import com.ostad.securenote.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note createNote(Note note, String username) {
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByOwner(String username) {
        return noteRepository.findByOwnerUsername(username);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public Note updateNote(Long id, Note updatedData, String username) {
        Note note = getNoteById(id);
        if (!note.getOwnerUsername().equals(username)) {
            throw new RuntimeException("Access Denied: You do not own this note");
        }
        note.setTitle(updatedData.getTitle());
        note.setContent(updatedData.getContent());
        return noteRepository.save(note);
    }

    public void deleteNoteAsUser(Long id, String username) {
        Note note = getNoteById(id);
        if (!note.getOwnerUsername().equals(username)) {
            throw new RuntimeException("Access Denied: You do not own this note");
        }
        noteRepository.delete(note);
    }

    // Admin specific methods
    public List<Note> getAllNotesInSystem() {
        return noteRepository.findAll();
    }

    public void deleteAnyNoteAsAdmin(Long id) {
        noteRepository.deleteById(id);
    }
}