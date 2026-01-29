package com.example.auth_service.app.services;

import com.example.auth_service.app.interfaces.IUserService;
import com.example.auth_service.infra.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IUserRepository userRepository;

    // Ici, tu peux injecter un repository pour charger user depuis BDD
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.auth_service.models.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authority)
                .build();
    }
}
