package com.ostad.securenote.service;

import com.ostad.securenote.dto.NoteRequest;
import com.ostad.securenote.exception.ResourceNotFoundException;
import com.ostad.securenote.entity.Note;
import com.ostad.securenote.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;


    public Note createNote(NoteRequest request, String ownerUsername) {
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .ownerUsername(ownerUsername)
                .build();
        return noteRepository.save(note);
    }


    public List<Note> getMyNotes(String ownerUsername) {
        return noteRepository.findByOwnerUsername(ownerUsername);
    }


    public Note getNoteById(Long id, String ownerUsername) {
        return noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to access it"));
    }


    public Note updateNote(Long id, NoteRequest request, String ownerUsername) {
        Note note = noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to update it"));

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return noteRepository.save(note);
    }


    public void deleteNote(Long id, String ownerUsername) {
        Note note = noteRepository.findByIdAndOwnerUsername(id, ownerUsername)
                .orElseThrow(() -> new AccessDeniedException(
                        "Note not found or you do not have permission to delete it"));
        noteRepository.delete(note);
    }



    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    public void adminDeleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", id));
        noteRepository.delete(note);
    }
}