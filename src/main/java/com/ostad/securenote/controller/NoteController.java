package com.ostad.securenote.controller;

import com.ostad.securenote.entity.Note;
import com.ostad.securenote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")

@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    private String getAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note, getAuthUsername());
    }

    @GetMapping
    public List<Note> getAllMyNotes() {
        return noteService.getNotesByOwner(getAuthUsername());
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        if (!note.getOwnerUsername().equals(getAuthUsername())) {
            throw new RuntimeException("Access Denied");
        }
        return note;
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updatedData) {
        return noteService.updateNote(id, updatedData, getAuthUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNoteAsUser(id, getAuthUsername());
    }
}