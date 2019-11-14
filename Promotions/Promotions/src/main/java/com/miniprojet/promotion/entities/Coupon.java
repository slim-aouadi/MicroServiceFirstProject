package com.miniprojet.promotion.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coupon {
	@Id
	@GeneratedValue
	private int id;
	private String code;
	private double pourcentage;
	private String date_debut;
	private String date_fin;
	private boolean etat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public String getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}
	public Coupon(String code, double pourcentage, String date_debut, String date_fin, boolean etat) {
		super();
		this.code = code;
		this.pourcentage = pourcentage;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.etat = etat;
	}
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean getEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
}
