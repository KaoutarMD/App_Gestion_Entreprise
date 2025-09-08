package com.Entreprise.GestionEntreprise.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Entreprise.GestionEntreprise.repositories.userRepository;
import com.Entreprise.GestionEntreprise.models.User;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private userRepository userRepository;
    
    public User saveUser(User user) {
    	  System.out.println("\n in the service :"+user.toString());
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepository.save(user);
        return user;
    }

	

	public Optional<User> findUserById(Long userid) {
		return userRepository.findById(userid);
	}



	public User findbyusername(String username) {
		
		return userRepository.findByUsername(username).get();
	}
}
