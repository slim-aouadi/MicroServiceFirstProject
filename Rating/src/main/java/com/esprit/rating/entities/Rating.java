package com.esprit.rating.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating implements Serializable {
	private static final long serialVersionUID = 6;
	@Id
	@GeneratedValue
	private int id;
	private int rate;
	private int produitId;
	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rating(int id, int rate, int produitId) {
		super();
		this.id = id;
		this.rate = rate;
		this.produitId = produitId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getProduitId() {
		return produitId;
	}
	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}
	
	

}
