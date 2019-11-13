package com.miniprojet.promotion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	@Query("select c from Coupon c where c.code = :code")
	public List<Coupon> rechercheCode(@Param("code") String nom);
	
	@Query("select c from Coupon c where c.etat = :etat")
	public List<Coupon> getCouponsEtat(@Param("etat") boolean etat);
}
