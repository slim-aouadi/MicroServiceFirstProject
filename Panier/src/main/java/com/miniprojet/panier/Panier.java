package com.miniprojet.panier;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Panier implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private int userId;
	private String date_creation;
	private boolean live;
	private double prixTotal;
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Panier(int id, int userId, String date_creation, boolean live) {
		super();
		this.id = id;
		this.userId = userId;
		this.date_creation = date_creation;
		this.live = live;
	}
	public double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

}
