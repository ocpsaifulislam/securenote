package com.ostad.securenote.controller;

import com.ostad.securenote.entity.Note;
import com.ostad.securenote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final NoteService noteService;


    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }


    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Map<String, String>> deleteAnyNote(@PathVariable Long id) {
        noteService.adminDeleteNote(id);   // throws ResourceNotFoundException → 404 via handler
        return ResponseEntity.ok(Map.of("message", "Note " + id + " deleted by admin"));
    }
}