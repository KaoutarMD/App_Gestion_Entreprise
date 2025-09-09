package com.Entreprise.GestionEntreprise.services;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.User;
import com.Entreprise.GestionEntreprise.repositories.EmployeRepository;
import com.Entreprise.GestionEntreprise.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private userRepository userRepository;
    // Create or Update an employee
    public Employe saveOrUpdateEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    // Get all employees
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employe> getEmployeById(Long id) {
        return employeRepository.findById(id);
    }

    // Delete employee by ID
    public void deleteEmploye(Long id) {
    	 System.out.println("user id " + id);
        employeRepository.deleteById(id);
        
    }

	public Employe findbyuser(User u) {
		
		return employeRepository.findByUser(u);
	}

   

	
}
