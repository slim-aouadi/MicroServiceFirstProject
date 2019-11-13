package com.miniprojet.panier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commande {
	@Id
	@GeneratedValue
	private int id;
	private String date_commande; 
	private int userId;
	private double prixTotal;
	private boolean live;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(String date_commande) {
		this.date_commande = date_commande;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}
	
	public Commande() {
		super();
	}
	public Commande(String date_commande, int userId, double prixTotal) {
		super();
		this.date_commande = date_commande;
		this.userId = userId;
		this.prixTotal = prixTotal;
		this.setLive(true);
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
}
