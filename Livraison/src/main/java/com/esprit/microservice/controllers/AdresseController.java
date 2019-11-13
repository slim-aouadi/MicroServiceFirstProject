package com.esprit.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.microservice.models.Adresse;
import com.esprit.microservice.repositories.AdresseRepository;



@RestController
@RequestMapping("/adresse")
public class AdresseController {
	
	
	@Autowired
	private AdresseRepository adresseRepository;
	
	
	@PostMapping("/addAdresse")
	private Adresse addAdresse(@RequestBody Adresse adresse) {
		adresseRepository.save(adresse);
		return adresse;
	}
	
	@GetMapping("/getAdresses")
	private List<Adresse> getAllAdresse()
	{
		return (List<Adresse>) adresseRepository.findAll();
	}
	
	
	

}
