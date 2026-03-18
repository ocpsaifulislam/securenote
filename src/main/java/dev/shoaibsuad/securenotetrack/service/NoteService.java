package dev.shoaibsuad.securenotetrack.service;

import dev.shoaibsuad.securenotetrack.entity.User;
import dev.shoaibsuad.securenotetrack.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<User> getAllNotes() {
        return noteRepository.findAll();
    }
    public  List<User> findAllById(Integer id){
        return noteRepository.findAllById(id);
    }
}
