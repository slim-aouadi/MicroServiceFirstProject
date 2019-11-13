package com.esprit.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.esprit.microservice.models.Utilisateur;
import com.esprit.microservice.repositories.UtilisateurRepository;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PostMapping("/addUtilisateur")
	private Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
		return utilisateur;
	}
	
	@GetMapping("/getUtilisateur")
	private List<Utilisateur> getAllUtilisateur()
	{
		return (List<Utilisateur>) utilisateurRepository.findAll();
	}
	

}
