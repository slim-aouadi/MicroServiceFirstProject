package com.miniprojet.promotion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Integer>{
	@Query("select c from UserCoupon c where c.userId = :userId and c.couponId = :couponId")
	public List<UserCoupon> userCouponExiste(@Param("userId") int userId, @Param("couponId") int couponId);

	@Query("select c from UserCoupon c where c.userId = :userId")
	public List<UserCoupon> getUserCoupons(@Param("userId") int userId);
	
	@Query("select c from UserCoupon c where c.couponId = :couponId and valable = :valable")
	public List<UserCoupon> getByCouponId(@Param("couponId") int couponId, @Param("valable") boolean valable);
}
