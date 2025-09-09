package com.Entreprise.GestionEntreprise.repositories;

import com.Entreprise.GestionEntreprise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {
    
 
    Optional<User> findByUsername(String username);

}
