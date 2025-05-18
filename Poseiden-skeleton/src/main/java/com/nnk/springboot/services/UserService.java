package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs.
 * Fournit les opérations CRUD de base ainsi que la logique d'enregistrement sécurisée.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Enregistre un nouvel utilisateur avec mot de passe chiffré et rôle par défaut.
     *
     * @param user L'utilisateur à enregistrer
     * @return L'utilisateur sauvegardé
     */
    public User saveUser(User user) {
        // Vérifie si le nom d'utilisateur existe déjà
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà utilisé");
        }

        // Validation du mot de passe
        validatePassword(user.getPassword());


        // Rôle par défaut
        user.setRole("USER");

        // Hash du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));



        return userRepository.save(user);
    }

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur
     * @return Option contenant l'utilisateur ou vide
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Validation du mot de passe dans un format personnalisé
     * @param password
     */

    private void validatePassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères, une majuscule, un chiffre et un symbole.");
        }
    }

}
