package com.esprit.microservice.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.microservice.models.Livraison;
import com.esprit.microservice.repositories.LivraisonRepository;

@RestController
@RequestMapping("/livraison")
public class LivraisonController {

	
	@Autowired
	private LivraisonRepository livraisonRepository;
	

	@PostMapping("/addLivraison")
	private Livraison addAdresse(@RequestBody Livraison livraison) {
		//livraison.setEtat(0);
		livraisonRepository.save(livraison);
		return livraison;
	}
	
	@GetMapping("/getLivraison")
	private List<Livraison> getAlllivraison()
	{
		return (List<Livraison>) livraisonRepository.findAll();
	}
	
	@GetMapping("/getLivraison/{id}")
	private Livraison getAlllivraisonById(@PathVariable(value = "id") String id)
	{
		return livraisonRepository.getOne(Integer.parseInt(id));
	}
	
	@DeleteMapping("/delete/{id}")
	private String deleteLivraisonById(@PathVariable(value = "id") String id) {
		livraisonRepository.delete(livraisonRepository.getOne(Integer.parseInt(id)));
		return id;
	}
	
	
	@GetMapping("/getDays")
	private int calculJour(@RequestBody Livraison livraison) {
		Date today = new Date();
		long diffInMillies = Math.abs(livraison.getDateLivraison().getTime() - today.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		System.out.println("jours "+diff);
		return (int)diff;
	}
	
	@GetMapping("/proposition")
	private Date dateProposition(List<Livraison> allLivraisonByClient) {
		int maxDays = calculJour(allLivraisonByClient.get(0));
		Date date = allLivraisonByClient.get(0).getDateLivraison() ;
		for(int i=1;i< allLivraisonByClient.size();i++)
		{
			if(calculJour(allLivraisonByClient.get(i))>maxDays) 
			{
				maxDays = calculJour(allLivraisonByClient.get(i));
				date = allLivraisonByClient.get(i).getDateLivraison();
			}
		}
		System.out.println("je vous propose tous les produits dans "+maxDays+" jours");
		return date;
	}
	
	@GetMapping("/getLivraisonByClient/{id}")
	private List<Livraison> getAllLivraisonByClient(@PathVariable(value = "id") String id){
		List<Livraison> alllivraison = livraisonRepository.findAll();
		List<Livraison> livraisonByclient = new ArrayList<Livraison>() ;
		for (Livraison l : alllivraison) {
			if((l.getClient().getId()==Integer.parseInt(id))&&(l.getEtat()==0)) {
				livraisonByclient.add(l);
			}
		}
		//System.out.println(livraisonByclient);
		System.out.println("a la date "+dateProposition(livraisonByclient));
		return livraisonByclient;
	}
	
	
	
	
	@PutMapping("/acceptProposition/{id}")
	private String acceptProposition(@PathVariable(value = "id") String id)
	{
		List<Livraison> allLivraisonByClient = getAllLivraisonByClient(id);
		Date dateProp = dateProposition(allLivraisonByClient);
		for (Livraison livraison : allLivraisonByClient) {
			livraison.setDateLivraison(dateProp);
			livraisonRepository.save(livraison);
		}
		return "accepted";
	}
	
	
	
	

	
	
}
