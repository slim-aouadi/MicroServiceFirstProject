package com.miniprojet.panier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.miniprojet.panier.*;
@RestController
@RequestMapping(value = "/api/panier")
public class PanierRestApi {
	@Autowired
	private PanierService panierService;
	private PanierItemService panierItemService;

	@PostMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Panier> createPanier(@PathVariable(value = "userId") int userId) {
		return new ResponseEntity<>(panierService.addPanier(userId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Panier> updatePanier(@PathVariable(value = "id") int id, @RequestBody Panier panier){
		return new ResponseEntity<>(panierService.updatePanier(id, panier.isLive(),0), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Panier>> getPaniers(@PathVariable(value = "userId") int userId) {
		return new ResponseEntity<>(panierService.getPaniers(userId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/confirmer/{panierId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Commande> confirmerCommande(@PathVariable(value = "panierId") int panierId) {
		return new ResponseEntity<>(panierService.confirmerCommande(panierId), HttpStatus.OK);
	}
}