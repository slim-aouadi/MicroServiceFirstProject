package com.miniprojet.panier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface PanierItemRepository extends JpaRepository<PanierItem, Integer>{
	@Query("select i from PanierItem i where i.productId = :productId and i.panierId = :panierId")
	public List<PanierItem> findProductItem(@Param("productId") int productId, @Param("panierId") int panierId);

	@Query("select i from PanierItem i where i.panierId = :panierId")
	public List<PanierItem> getItemsPanier(@Param("panierId") int panierId);

}
