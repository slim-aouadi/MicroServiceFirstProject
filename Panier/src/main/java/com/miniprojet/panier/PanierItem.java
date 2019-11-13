package com.miniprojet.panier;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PanierItem implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private int productId;
	private int quantity;
	private int panierId;
	private double prix;
	private String date_creation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPanierId() {
		return panierId;
	}
	public void setPanierId(int panierId) {
		this.panierId = panierId;
	}
	public String getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}
	public PanierItem() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public PanierItem(int productId, int quantity, int panierId, double prix, String date_creation) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.panierId = panierId;
		this.prix = prix;
		this.date_creation = date_creation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
