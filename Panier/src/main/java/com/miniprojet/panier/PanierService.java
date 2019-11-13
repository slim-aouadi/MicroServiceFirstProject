package com.miniprojet.panier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PanierService {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private CommandeRepository commandeRepository;

	public Panier addPanier(int userId) {
		Panier panier = new Panier();
		panier.setLive(true);
		panier.setUserId(userId);
		
		Date date = new Date();
        String date_creation = sdf.format(date);
		panier.setDate_creation(date_creation);
		return panierRepository.save(panier);
	}
	public Panier updatePanier(int id, boolean live, double prixAjoute) {
		if (panierRepository.findById(id).isPresent()) {
			Panier existingPanier = panierRepository.findById(id).get();
			existingPanier.setLive(live);
			double prixTotal = existingPanier.getPrixTotal() + prixAjoute;
			existingPanier.setPrixTotal(prixTotal);
			return panierRepository.save(existingPanier);
		} else
			return null;
	}
	public String deletePanier(int id) {
		if (panierRepository.findById(id).isPresent()) {
			panierRepository.deleteById(id);
			return "panier supprimé";
		} else
			return "panier non supprimé";
	}
	public List<Panier> getPaniers(int userId) {
		return panierRepository.panierUser(userId);
	}
	public Panier getPanier(int id) {
		return panierRepository.findById(id).get();
	}
	public Commande confirmerCommande(int panierId) {
		if (panierRepository.findById(panierId).isPresent()) {
			Panier panier = panierRepository.findById(panierId).get();
			Date date = new Date();
	        String date_commande = sdf.format(date);
			Commande commande = new Commande(date_commande, panier.getUserId(), panier.getPrixTotal());
			return commandeRepository.save(commande);
		} else
			return null;
		
	}
}
