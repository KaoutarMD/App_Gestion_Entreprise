package com.Entreprise.GestionEntreprise.services;

import com.Entreprise.GestionEntreprise.models.Employe;
import com.Entreprise.GestionEntreprise.models.Fiche;
import com.Entreprise.GestionEntreprise.repositories.FicheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FicheService {

    @Autowired
    private FicheRepository ficheRepository;

    // Get all salary slips (fiche)
    public List<Fiche> getAllFiches() {
        return ficheRepository.findAll();
    }

    // Get a specific salary slip by its ID
    public Optional<Fiche> getFicheById(int id) {
        return ficheRepository.findById(id);
    }

    // Save or update a salary slip
    public Fiche saveOrUpdateFiche(Fiche fiche) {
    	double montant=fiche.getMontantbrut();
    	double taxe=fiche.getTaxe();
    	double montantnet=montant-(montant*taxe/100);
    	fiche.setMontantnet(montantnet);
        return ficheRepository.save(fiche);
    }

    // Delete a salary slip by its ID
    public void deleteFiche(int id) {
        ficheRepository.deleteById(id);
    }

	

	public List<Fiche> findByemploye(Employe emp) {
		// TODO Auto-generated method stub
		return ficheRepository.findByEmp(emp);
	}
}
