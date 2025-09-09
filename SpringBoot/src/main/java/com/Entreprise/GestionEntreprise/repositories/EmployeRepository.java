package com.Entreprise.GestionEntreprise.repositories;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

	Employe findByUser(User u);
   
	
}