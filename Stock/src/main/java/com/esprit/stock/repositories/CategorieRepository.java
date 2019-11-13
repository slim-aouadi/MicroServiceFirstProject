package com.esprit.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.stock.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
