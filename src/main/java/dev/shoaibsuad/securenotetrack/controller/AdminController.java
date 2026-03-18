package dev.shoaibsuad.securenotetrack.controller;

import dev.shoaibsuad.securenotetrack.entity.Note;
import dev.shoaibsuad.securenotetrack.entity.User;
import dev.shoaibsuad.securenotetrack.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/notes")
@RequiredArgsConstructor
public class AdminController {
    private final NoteService noteService;

    @GetMapping()
    public List<User> getAllUsers() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAnyNote(@PathVariable Long id) {
        noteService.adminDeleteNote(id);   // throws ResourceNotFoundException → 404 via handler
        return ResponseEntity.ok(Map.of("message", "Note " + id + " deleted by admin"));
    }
}
