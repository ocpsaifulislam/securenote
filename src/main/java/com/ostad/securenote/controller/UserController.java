package com.ostad.securenote.controller;

import com.ostad.securenote.entity.Note;
import com.ostad.securenote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class UserController {
    private final NoteService noteService;

    @PostMapping
    public Note create(@RequestBody Note note, Principal principal) {
        return noteService.createNote(note, principal.getName());
    }

    @GetMapping
    public List<Note> getAll(Principal principal) {
        return noteService.getNotesByUser(principal.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        noteService.deleteNoteForUser(id, principal.getName());
    }
}
