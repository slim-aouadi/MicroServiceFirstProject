package com.miniprojet.panier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PanierRepository extends JpaRepository<Panier, Integer>{
	@Query("select p from Panier p where p.userId = :userId and p.live = true")
	public List<Panier> panierUser(@Param("userId") int userId);
}