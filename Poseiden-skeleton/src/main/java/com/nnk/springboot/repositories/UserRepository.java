package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Recherche un utilisateur par son nom d'utilisateur (login).
     * @param username le nom d'utilisateur
     * @return l'entité User correspondante, ou null si aucun trouvé
     */
    Optional<User> findByUsername(String username);


}
