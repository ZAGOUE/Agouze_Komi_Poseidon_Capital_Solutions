package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de UserDetailsService utilisée par Spring Security
 * pour charger les utilisateurs depuis la base de données.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Recherche l'utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur fourni lors du login
     * @return un objet UserDetails pour l'authentification
     * @throws UsernameNotFoundException si l'utilisateur n'existe pas
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("[DEBUG] Tentative de login pour l'utilisateur : " + username);

        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("[DEBUG] Utilisateur introuvable : " + username);
                    return new UsernameNotFoundException("Utilisateur introuvable : " + username);
                });

        System.out.println("[DEBUG] Utilisateur trouvé : " + appUser.getUsername() + ", rôle = " + appUser.getRole());

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }

}
