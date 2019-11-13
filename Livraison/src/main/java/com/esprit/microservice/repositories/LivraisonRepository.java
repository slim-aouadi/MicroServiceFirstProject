package com.esprit.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.microservice.models.Livraison;

public interface LivraisonRepository extends JpaRepository<Livraison, Integer> {

}
