package com.esprit.stock.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.stock.entities.Categorie;
import com.esprit.stock.repositories.CategorieRepository;

@RestController
public class CategorieRestApi {


	@Autowired
	public CategorieRepository categorieRepository;
	
	@GetMapping
	public List<Categorie> getAllStock() {
		return categorieRepository.findAll();
	}
	
	@PostMapping
	public String addCategorie(@RequestBody Categorie categorie) {
		 categorieRepository.save(categorie);
		 return "Categorie added";
	}
	
	@GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public Categorie getStockById(@PathVariable(value="id") int id) {
		return categorieRepository.findById(id).get();
	}
}
