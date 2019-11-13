package com.esprit.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.stock.entities.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	@Query(value = "SELECT * FROM Produit p WHERE p.nom = :nom", nativeQuery = true)		
	public Produit getProduitByNom(@Param("nom") String nom);


}
