package com.miniprojet.panier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PanierItemService {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	@Autowired
	private PanierItemRepository panierItemRepository;
	@Autowired
	private PanierService panierService;
	
	public PanierItem addItem(int userId, int produitId, double prixItem, int quantite) {
		List<Panier> userPaniers = panierService.getPaniers(userId);
		int panierId = 0;
		if (userPaniers.size() > 0 && userPaniers.get(0) != null) {
			panierId = userPaniers.get(0).getId();
		}
		else {
			Panier newPanier = panierService.addPanier(userId);
			panierId = newPanier.getId();
		}
		List<PanierItem> items = panierItemRepository.findProductItem(produitId, panierId);
		double prixQuantite = this.addedItemPrice(quantite, prixItem);
		if (items.size() > 0) {
			PanierItem item = items.get(0);
			double newPrice = item.getPrix() + prixQuantite;
			int newQuantite = item.getQuantity() + quantite;
			item.setPrix(newPrice);
			item.setQuantity(newQuantite);
			panierService.updatePanier(panierId, true, prixQuantite);
			return panierItemRepository.save(item);
		}
		
		Date date = new Date();
        String date_creation = sdf.format(date);
        PanierItem newItem = new PanierItem(produitId, quantite, panierId, prixQuantite, date_creation);
        panierService.updatePanier(panierId, true, prixQuantite);
		return panierItemRepository.save(newItem);
	}
	
	public List<PanierItem> getItemsPanier(int panierId) {
		return panierItemRepository.getItemsPanier(panierId);
	}
	
	public String deleteItem(int id) {
		if (panierItemRepository.findById(id).isPresent()) {
			PanierItem item = panierItemRepository.findById(id).get();
			double prix = -item.getPrix();
			panierService.updatePanier(item.getPanierId(), true, prix);
			panierItemRepository.deleteById(id);
			return "Produit supprimé du panier";
		} else
			return "Produit non supprimé du panier";
	}
	
	private double addedItemPrice(int quantite, double prixItem) {
		return quantite * prixItem;
	}
}
