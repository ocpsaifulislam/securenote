package dev.shoaibsuad.securenotetrack.controller;

import dev.shoaibsuad.securenotetrack.entity.Note;
import dev.shoaibsuad.securenotetrack.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping()
    public Map<String, String> createNote(
            @Valid @RequestBody Note note,
            @AuthenticationPrincipal UserDetails currentUser) {
        String result = noteService.createNote(note, currentUser.getUsername());
        return Map.of("message", result);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getMyNotes(
            @AuthenticationPrincipal UserDetails currentUser) {
        List<Note> notes = noteService.getMyNotes(currentUser.getUsername());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(
            @PathVariable Integer id,
            @AuthenticationPrincipal UserDetails currentUser) {
        Note note = noteService.getNoteById(id, currentUser.getUsername());
        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Integer id,
            @Valid @RequestBody Note updatedNote,
            @AuthenticationPrincipal UserDetails currentUser) {

        Note savedNote = noteService.updateNote(id, updatedNote, currentUser.getUsername());
        System.out.println(savedNote);
        return ResponseEntity.ok(savedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteNote(
            @PathVariable Integer id,
            @AuthenticationPrincipal UserDetails currentUser) {

        noteService.deleteNote(id, currentUser.getUsername());
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }
}
