package dev.shoaibsuad.securenotetrack.controller;

import dev.shoaibsuad.securenotetrack.entity.Note;
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
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAnyNote(@PathVariable Integer id) {
        noteService.adminDeleteNote(id);
        return ResponseEntity.ok(Map.of("message", "Note " + id + " deleted by admin"));
    }
}
