package com.Entreprise.GestionEntreprise.repositories;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.Fiche;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicheRepository extends JpaRepository<Fiche, Integer> {

	List<Fiche> findByEmp(Employe emp);
   
}
