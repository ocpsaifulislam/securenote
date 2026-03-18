package dev.shoaibsuad.securenotetrack.service;

import dev.shoaibsuad.securenotetrack.entity.User;
import dev.shoaibsuad.securenotetrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public String registerUser(User user, String role) {
        prepareUser(user, role);

        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user = userRepository.save(user);
        if (user.getId() == null) {
            return "Failed to register user";
        } else {
            return "User registered successfully with ID: " + user.getId();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private void prepareUser(User user, String role) {
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
    }
}
