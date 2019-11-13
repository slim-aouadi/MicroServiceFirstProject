package com.esprit.microservice.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Livraison implements Serializable {


	private static final long serialVersionUID = 6;
	
	@Id
	@GeneratedValue
	private int id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateLivraison;
	
	@OneToOne
	private Adresse destination;
	@OneToOne
	private Utilisateur client;
	private int numCommande;
	private int reduction;
	private int fraisLivraisonEstime;
	private int etat;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public Adresse getDestination() {
		return destination;
	}
	public void setDestination(Adresse destination) {
		this.destination = destination;
	}
	
	public Utilisateur getClient() {
		return client;
	}
	public void setClient(Utilisateur client) {
		this.client = client;
	}
	public int getNumCommande() {
		return numCommande;
	}
	public void setNumCommande(int numCommande) {
		this.numCommande = numCommande;
	}
	public int getReduction() {
		return reduction;
	}
	public void setReduction(int reduction) {
		this.reduction = reduction;
	}
	
	public int getFraisLivraisonEstime() {
		return fraisLivraisonEstime;
	}
	public void setFraisLivraisonEstime(int fraisLivraisonEstime) {
		this.fraisLivraisonEstime = fraisLivraisonEstime;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public Livraison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Livraison(int id, Date dateLivraison, Adresse destination, Utilisateur client, int numCommande,
			int reduction, int fraisLivraisonEstime, int etat) {
		super();
		this.id = id;
		this.dateLivraison = dateLivraison;
		this.destination = destination;
		this.client = client;
		this.numCommande = numCommande;
		this.reduction = reduction;
		this.fraisLivraisonEstime = fraisLivraisonEstime;
		this.etat = etat;
	}
	
	
	
	
	
	
	
	
	
	

}
