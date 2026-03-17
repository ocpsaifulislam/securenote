package com.ostad.securenote.controller;

import com.ostad.securenote.dto.NoteRequest;
import com.ostad.securenote.dto.NoteResponse;
import com.ostad.securenote.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        NoteResponse created = NoteResponse.from(
                noteService.createNote(request, currentUser.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getMyNotes(
            @AuthenticationPrincipal UserDetails currentUser) {

        List<NoteResponse> notes = noteService
                .getMyNotes(currentUser.getUsername())
                .stream()
                .map(NoteResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails currentUser) {

        NoteResponse note = NoteResponse.from(
                noteService.getNoteById(id, currentUser.getUsername()));
        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        NoteResponse updated = NoteResponse.from(
                noteService.updateNote(id, request, currentUser.getUsername()));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteNote(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails currentUser) {

        noteService.deleteNote(id, currentUser.getUsername());
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }
}