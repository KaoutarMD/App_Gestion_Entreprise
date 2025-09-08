package com.Entreprise.GestionEntreprise.controllers;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.Fiche;
import com.Entreprise.GestionEntreprise.models.User;
import com.Entreprise.GestionEntreprise.services.EmployeService;
import com.Entreprise.GestionEntreprise.services.UserService;
import com.Entreprise.GestionEntreprise.services.*;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private UserService userService;
    @Autowired
    private FicheService ficheService;
/*----------------------Employe-------------------------------------------------*/
    
    // Tableau de bord de l'admin
    @GetMapping
    public String adminDashboard(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "admin/indexadmin";
    }

    // Afficher les employés
    @GetMapping("/Afficher")
    public String showEmployees(Authentication authentication,Model model) {
        List<Employe> employes = employeService.getAllEmployes();
        model.addAttribute("employes", employes); 
        return "admin/GestionEmploye";
    }

    

    // Obtenir un employé par ID (API REST)
    @GetMapping("/Afficher/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Optional<Employe> employe = employeService.getEmployeById(id);
        return employe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/Modifier/{id}")
    public String showModifyForm(@PathVariable("id") Long id, Model model) {
        Optional<Employe> employeOptional = employeService.getEmployeById(id);
        if (employeOptional.isPresent()) {
            model.addAttribute("employe", employeOptional.get());
            return "admin/ModifierEmploye"; // Créez cette page pour modifier un employé
        } else {
            return "redirect:/admin/Afficher"; // Rediriger si l'employé n'existe pas
        }
    }


    @PostMapping("/Save")
    public String saveEmploye(@ModelAttribute("employe") Employe employe, @RequestParam("userid") Long userid) {
    	
    		   System.out.println("user id " + userid);
    		   System.out.println("\n employe " + employe.toString());
             User user = userService.findUserById(userid).get();
             System.out.println("\n user  " + user.toString());
                   
                 employe.setUser(user); 
                 employeService.saveOrUpdateEmploye(employe);  

                 return "redirect:/admin/Afficher"; 
             
     }
    




    @PostMapping("/Delete/{id}")
    public String deleteEmploye(@PathVariable("id") Long id) {
        System.out.println("Attempting to delete employee with ID: " + id);
        try {
            employeService.deleteEmploye(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/admin/Afficher"; // Redirect after deletion
    }
    
    
    
    
    

    @GetMapping("/afficherAjouter")
    public String showAddForm( Model model) {
       Employe e=new Employe();
            model.addAttribute("employe",e);
         
            return "admin/AjouterEmploye"; 
           
    }


 
    @PostMapping("/Ajouter")
    public String addEmploye(Model model,@ModelAttribute("employe") Employe employe) {
        
        System.out.println("\n employe " + employe.toString());
        String pass=generatePassword();
 
        User user = new User();
        user.setUsername(employe.getNom());
        user.setPassword(pass);
        user.setRole("EMPLOYE");             
        
        userService.saveUser(user);
        System.out.println("\n user  " + user.toString());
        
        // Set the created user to the employee
        employe.setUser(user); 
       
        model.addAttribute("pass",pass);
        // Save or update the employee
        employeService.saveOrUpdateEmploye(employe);  
        System.out.println("\n Employe after save ID: " + employe.getId()); 
        return "admin/AffichagePassword"; 
    }

    @GetMapping("/AffichagePassword")
    public String AffichagePassword( Model model) {
            return "admin/AffichagePassword"; 
           
    }
    
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();  // This will handle password encryption
    }
    public static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(3);

        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }
    /*----------------------Fiche de salaire-------------------------------------------------*/
    // Afficher les fiches de salaire
    @GetMapping("/fichesAfficher")
    public String showSalarySlips(Model model) {
        List<Fiche> fiches = ficheService.getAllFiches();
        model.addAttribute("fiches", fiches);
        return "admin/GestionFiche"; // Create a page to display salary slips
    }

    // Ajouter une nouvelle fiche de salaire
    @GetMapping("/fichesAjouter")
    public String showAddSalarySlipForm(Model model) {
        Fiche fiche = new Fiche();
        model.addAttribute("fiche", fiche);

        List<Employe> employes = employeService.getAllEmployes(); // List of employees to associate with the salary slip
        model.addAttribute("employes", employes);

        return "admin/AjouterFiche"; 
    }

    @PostMapping("/fichesAjouter")
    public String addSalarySlip(@ModelAttribute("fiche") Fiche fiche, @RequestParam("employeId") Long employeId) {
    	System.out.println("\n l employe id est "+employeId);
    	
        Optional<Employe> employeOptional = employeService.getEmployeById(employeId);
        System.out.println("\n fiche before save "+fiche.toString());
        if (employeOptional.isPresent()) {
            fiche.setEmp(employeOptional.get()); 
            ficheService.saveOrUpdateFiche(fiche); 
        }
        System.out.println("\n fiche after save est "+fiche.toString());
        return "redirect:/admin/fichesAfficher";
    }

    // Modifier une fiche de salaire
    @GetMapping("/ModifierFiche/{id}")
    public String showModifySalarySlipForm(@PathVariable("id") int id, Model model) {
    	System.out.println("\n id before modification est"+id);
    	
        Optional<Fiche> ficheOptional = ficheService.getFicheById(id);
        if (ficheOptional.isPresent()) {
            model.addAttribute("fiche", ficheOptional.get());
            List<Employe> employes = employeService.getAllEmployes(); 
            model.addAttribute("employes", employes);
            return "admin/ModifierFiche";
        } else {
            return "admin/fichesAfficher"; 
        }
    }

    @PostMapping("/ModifierFiche/{numfiche}")
    public String modifySalarySlip(@PathVariable("numfiche") int numfiche, @ModelAttribute("fiche") Fiche fiche, @RequestParam("employeId") Long employeId) {
    	System.out.println("\n id before modification est"+numfiche);
    	fiche.setNumfiche(numfiche);
    	//System.out.println("\n fiche before modification est"+fiche.toString());
    	//System.out.println("\n emp before modification est"+employeId);
    	
    	
        Optional<Employe> employeOptional = employeService.getEmployeById(employeId);
        
        
            fiche.setEmp(employeOptional.get()); // Set the employee to the salary slip
           
            ficheService.saveOrUpdateFiche(fiche); // Update the salary slip
        

        return "redirect:/admin/fichesAfficher";
    }

    // Supprimer une fiche de salaire
    @PostMapping("/fichesDelete/{id}")
    public String deleteSalarySlip(@PathVariable("id") int id) {
    	System.out.println("id de la fiche"+id);
       
            ficheService.deleteFiche(id);
        
        return "redirect:/admin/fichesAfficher";
    }

    
    
}
