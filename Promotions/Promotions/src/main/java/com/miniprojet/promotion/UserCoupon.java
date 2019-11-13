package com.miniprojet.promotion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserCoupon {
	@Id
	@GeneratedValue
	private int id;
	private int userId;
	private int couponId;
	private boolean valable;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public UserCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isValable() {
		return valable;
	}
	public void setValable(boolean valable) {
		this.valable = valable;
	}
	public UserCoupon(int userId, int couponId, boolean valable) {
		super();
		this.userId = userId;
		this.couponId = couponId;
		this.valable = valable;
	}

}
