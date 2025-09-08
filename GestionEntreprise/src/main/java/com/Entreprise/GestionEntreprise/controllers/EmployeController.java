package com.Entreprise.GestionEntreprise.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.Fiche;
import com.Entreprise.GestionEntreprise.models.User;
import com.Entreprise.GestionEntreprise.services.EmployeService;
import com.Entreprise.GestionEntreprise.services.FicheService;
import com.Entreprise.GestionEntreprise.services.UserService;

@Controller
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private EmployeService employeService;
    @Autowired
    private FicheService ficheService;
    @Autowired
    private UserService userService;
    
    
    @GetMapping
    public String employeDashboard(Model model,Authentication authentication) {
    	
    
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        	
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username= userDetails.getUsername();
             User u=userService.findbyusername(username);
             System.out.println(u.toString());
           Employe emp=employeService.findbyuser(u);
           System.out.println(emp.toString());
             model.addAttribute("employe", emp); 
        
    	
    	List<Fiche> fiches = ficheService.findByemploye(emp);
    	 for (Fiche fiche : fiches) {
    		 System.out.println(fiche.toString());
    	 }
       model.addAttribute("fiches", fiches);
        }
        return "employe/indexemploye"; 
    }
    
    @GetMapping("/changerPassword")
    public String ChangerPassword(Model model,Authentication authentication) {
    	
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            String username= userDetails.getUsername();
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            
             User u=userService.findbyusername(username);
             System.out.println(u.toString());
             Employe emp=employeService.findbyuser(u);
             System.out.println(emp.toString());
               model.addAttribute("employe", emp); 
           
        
        return "employe/modifierPassword"; 
    }
    @PostMapping("/changerPassword")
    public String ChangerPassword2(Model model,@ModelAttribute("password") String password,Authentication authentication) {
    	
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            String username= userDetails.getUsername();
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            
             User u=userService.findbyusername(username);
             System.out.println(u.toString());
             Employe emp=employeService.findbyuser(u);
             System.out.println("\n the new password is "+password);
             model.addAttribute("employe", emp); 
             u.setPassword(password);
             System.out.println("\n after setting password"+u.toString());
             userService.saveUser(u);
             
        
        return "employe/modifierPassword"; 
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

