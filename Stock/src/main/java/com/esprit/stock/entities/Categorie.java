package com.esprit.stock.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Categorie implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String nomCategorie;
	
	@JsonIgnore
	@OneToMany(mappedBy="categorie")
	private List<Produit> listProduit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	
	public Categorie(int id, String nomCategorie, List<Produit> listProduit) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
		this.listProduit = listProduit;
	}
	public Categorie() {
		super();
	}
	
	
}