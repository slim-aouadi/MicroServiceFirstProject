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
@RequestMapping(value = "/api/panier/items")
public class PanierItemRestApi {
	@Autowired
	private PanierItemService panierItemService;

	@PostMapping(value = "/{userId}/{produitId}/{prixItem}/{quantite}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PanierItem> addItem(@PathVariable(value = "userId") int userId, @PathVariable(value = "produitId") int produitId, @PathVariable(value = "prixItem") int prixItem, @PathVariable(value = "quantite") int quantite) {
		return new ResponseEntity<>(panierItemService.addItem(userId, produitId, prixItem, quantite), HttpStatus.OK);
	}

	@GetMapping(value = "/{panierId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PanierItem>> addItem(@PathVariable(value = "panierId") int panierId) {
		return new ResponseEntity<>(panierItemService.getItemsPanier(panierId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{produitId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteItem(@PathVariable(value = "produitId") int produitId) {
		return new ResponseEntity<>(panierItemService.deleteItem(produitId), HttpStatus.OK);
	}
}