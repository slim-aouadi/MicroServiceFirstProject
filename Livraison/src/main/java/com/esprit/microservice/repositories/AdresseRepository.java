package com.esprit.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.microservice.models.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse, Integer> {

}
