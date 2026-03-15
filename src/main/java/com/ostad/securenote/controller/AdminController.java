package com.ostad.securenote.controller;


import com.ostad.securenote.entity.Note;
import com.ostad.securenote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/notes")
@RequiredArgsConstructor
public class AdminController {
    private final NoteService noteService;

    @GetMapping
    public List<Note> getAll() {
        return noteService.getAllNotes();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        noteService.adminDeleteNote(id);
    }
}