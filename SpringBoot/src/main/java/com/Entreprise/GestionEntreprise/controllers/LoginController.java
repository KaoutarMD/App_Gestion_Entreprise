package com.Entreprise.GestionEntreprise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;


import com.Entreprise.GestionEntreprise.models.User;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String login(Authentication authentication) {
        return "login"; 
    }
	
	@GetMapping("/logout")
    public String logout(Authentication authentication) {
        return "login"; 
    }
	
	@GetMapping("/default")
	public String defaultAfterLogin(Authentication authentication) {
	    
	    User user = (User) authentication.getPrincipal(); 

	    if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
	        return "redirect:/admin";
	    } else if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYE"))) {
	        return "redirect:/employe";
	    }
	    return "redirect:/error";
	}

}


