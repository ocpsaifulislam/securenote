package dev.shoaibsuad.securenotetrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/register/user").permitAll()
                                .requestMatchers("/api/auth/register/admin").permitAll()
                                .requestMatchers("/api/auth/register/all").permitAll()
                                .requestMatchers("/api/admin/notes").hasAuthority("admin".toUpperCase())
                                .requestMatchers("/api/admin/notes/").hasAuthority("admin".toUpperCase())
                                .requestMatchers("/api/notes/**").hasAuthority("user".toUpperCase())                                .anyRequest().authenticated()
                ).csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
