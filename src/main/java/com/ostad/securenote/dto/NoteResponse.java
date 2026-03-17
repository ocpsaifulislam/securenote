package com.ostad.securenote.dto;

import com.ostad.securenote.entity.Note;
import lombok.Data;

@Data
public class NoteResponse {

    private Long id;
    private String title;
    private String content;
    private String ownerUsername;

    public static NoteResponse from(Note note) {
        NoteResponse r = new NoteResponse();
        r.setId(note.getId());
        r.setTitle(note.getTitle());
        r.setContent(note.getContent());
        r.setOwnerUsername(note.getOwnerUsername());
        return r;
    }
}
