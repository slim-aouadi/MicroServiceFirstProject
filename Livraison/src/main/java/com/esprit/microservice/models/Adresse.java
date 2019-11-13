package com.esprit.microservice.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adresse implements Serializable{

	private static final long serialVersionUID = 6;
	
	@Id
	@GeneratedValue
	private int id;
	
	private String pays;
	private String ville;
	private String rue;
	private String codePostal;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Adresse(int id, String pays, String ville, String rue, String codePostal) {
		super();
		this.id = id;
		this.pays = pays;
		this.ville = ville;
		this.rue = rue;
		this.codePostal = codePostal;
	}
	
	
	
	
	
	
	

}
