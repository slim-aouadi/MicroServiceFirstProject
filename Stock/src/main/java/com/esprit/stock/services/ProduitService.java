package com.esprit.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.stock.entities.Categorie;
import com.esprit.stock.entities.Produit;
import com.esprit.stock.repositories.CategorieRepository;
import com.esprit.stock.repositories.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	public void ajoutProduit(Produit p) {
		produitRepository.save(p);
	}
	
	public List<Produit> listProduit() {
		return produitRepository.findAll();
	}
	
	public Produit getProduitById(int id) {
		return produitRepository.findById(id).get();
	}
	
	public Produit getProduitByNom(String nom) {
		return produitRepository.getProduitByNom(nom);
	}
	
	public String deleteProduitById(int id) {
		if(produitRepository.findById(id).isPresent()) {
			produitRepository.delete(produitRepository.findById(id).get());
			return "Deleted";
		}
		else {
			return "Not here";
		}
	}
	
	public String addProduitCategorie(int idProduit,int idCategorie) {
		Produit produit = produitRepository.findById(idProduit).get();
		Categorie categorie = categorieRepository.findById(idCategorie).get();
		System.out.println(produit.getNom());
		System.out.println(categorie.getNomCategorie());
		produit.setCategorie(categorie);
		produitRepository.save(produit);
		 return "Produit : "+produit.getNom()+" added to categorie : "+categorie.getNomCategorie();
	}
	
	public String updateProduit(int id,Produit p) {
		if(produitRepository.findById(id).isPresent()) {
			Produit oldProduit = produitRepository.findById(id).get();
			if(p.getNom() != null) {
				oldProduit.setNom(p.getNom());
			}
			if(p.getPrix() != 0) {
				oldProduit.setPrix(p.getPrix());
			}
			produitRepository.save(oldProduit);
			return "update";
		}else {
			return "not updated";
		}
	}
}
