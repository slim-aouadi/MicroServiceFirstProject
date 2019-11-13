package com.esprit.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.rating.entities.Rating;


public interface RatingRepository extends JpaRepository<Rating, Integer> {

	@Query(value = "SELECT SUM(p.rate)/count(p.rate) AS average FROM Rating p WHERE p.produit_id = :id", nativeQuery = true)		
	public Object getrateProduct(@Param("id") int id);
}
