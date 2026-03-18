package dev.shoaibsuad.securenotetrack.service;

import dev.shoaibsuad.securenotetrack.entity.Note;
import dev.shoaibsuad.securenotetrack.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public String createNote(Note note, String ownerUsername) {
        note.setId(null);
        note.setOwnerUsername(ownerUsername);
        note = noteRepository.save(note);
        if (note.getId() == null) {
            return "Failed to create note";
        } else {
            return "create note successfully with ID: " + note.getId();
        }
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void adminDeleteNote(Integer id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    public List<Note> getMyNotes(String ownerUsername) {
        return noteRepository.findByOwnerUsername(ownerUsername);
    }

    public Note getNoteById(Integer id, String ownerUsername) {
        return noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to access it"));
    }

    public Note updateNote(Integer id, Note updatedNote, String ownerUsername) {
        Note existingNote = noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to update it"));

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        return noteRepository.save(existingNote);
    }
    public void deleteNote(Integer id, String ownerUsername) {
        Note note = noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to delete it"));
        noteRepository.delete(note);
    }

}
