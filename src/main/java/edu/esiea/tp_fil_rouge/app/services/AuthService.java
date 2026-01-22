package edu.esiea.tp_fil_rouge.app.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager manager;

    public AuthService(AuthenticationManager manager) {
        this.manager = manager;
    }

    public String authenticate(String login, String password) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        return "token";
    }
}
