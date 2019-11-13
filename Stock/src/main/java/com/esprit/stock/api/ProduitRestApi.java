package com.esprit.stock.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.stock.entities.Categorie;
import com.esprit.stock.entities.Produit;
import com.esprit.stock.services.ProduitService;

@RestController
@RequestMapping(value = "/produit")
public class ProduitRestApi {

	@Autowired
	private ProduitService produitService;
	
	@PostMapping(value = "/addProduit")
	@ResponseStatus(HttpStatus.OK)
	public String addProduit(@RequestBody Produit p) {
		produitService.ajoutProduit(p);
		return "Success";
	}
	
	@GetMapping(value = "/listProduit")
	@ResponseStatus(HttpStatus.OK)
	public List<Produit> listProduit() {
		return produitService.listProduit();
		
	}
	
	
	@GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public Produit getProduitById(@PathVariable(value="id") int id) {
		return produitService.getProduitById(id);
	}
	
	@GetMapping(value="byNom/{nom}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public Produit getProduitByNom(@PathVariable(value="nom") String nom) {
		return produitService.getProduitByNom(nom);
	}
	
	
	@DeleteMapping(value="delete/{id}" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteProduitById(@PathVariable(value="id") int id) {
		return produitService.deleteProduitById(id);
	}
	
	@GetMapping(value="/addProduitCategorie/{idproduit}/{idcategorie}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public String addProduitCategorie(@PathVariable(value="idproduit") int idproduit , @PathVariable(value="idcategorie") int idcategorie) {
		return produitService.addProduitCategorie(idproduit, idcategorie);
	}
	
	
	@PutMapping(value="update/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateProduit(@PathVariable(value="id") int id, @RequestBody Produit produit) {
		return produitService.updateProduit(id, produit);
	}
	
	
}
