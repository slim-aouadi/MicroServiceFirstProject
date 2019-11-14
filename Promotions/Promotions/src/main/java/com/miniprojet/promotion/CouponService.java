package com.miniprojet.promotion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	private static final DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private UserCouponRepository userCouponRepository;
	
	public String ajouterCoupon(Coupon coupon) {
		if (couponRepository.rechercheCode(coupon.getCode()).size() > 0) { 
			return "Ce coupon existe, veuillez changer de code promo";
		}
		couponRepository.save(coupon);
		return "Coupon ajouté";
	}
	
	public List<Coupon> listeCouponsUtiliseables() { 
		List<Coupon> coupons = couponRepository.getCouponsEtat(true); 
		List<Coupon> result = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			boolean from = compareDatesBefore(coupon.getDate_debut()); 
			boolean to = compareDatesAfter(coupon.getDate_fin());
			if (from && to) {
				result.add(coupon); 
			}
		}
		
		return result;
	}
	
	public Coupon rechercherCode(String code) {
		List<Coupon> listeCoupons = couponRepository.rechercheCode(code); 
		if (listeCoupons.size() > 0 && listeCoupons.get(0) != null) {
			return listeCoupons.get(0);
		}
		return null;
	}
	
	public UserCoupon utiliserCode(int userId, String code) {
		Coupon coupon = rechercherCode(code);
		if (coupon != null && !(userCouponRepository.userCouponExiste(userId, coupon.getId()).size() > 0)) { 
			UserCoupon userCoupon = new UserCoupon(userId, coupon.getId(), true);
			userCoupon.setValable(true);
			return userCouponRepository.save(userCoupon);
		}
		
		System.out.println("ce code n'existe pas ou est dejà utilise par l'utilisateur");
		return null;
	}
	
	public double calculerPrix(int userId, double prix) { 
		
		List<UserCoupon> userCoupons = userCouponRepository.getUserCoupons(userId);
		double newPrix = prix;
		if (userCoupons.size() > 0) {
			for (UserCoupon userCoupon : userCoupons) {
				Coupon coupon = couponRepository.findById(userCoupon.getCouponId()).get(); 
				
				boolean from = compareDatesBefore(coupon.getDate_debut()); 
				boolean to = compareDatesAfter(coupon.getDate_fin());
				if (from && to && userCoupon.isValable()) { 
					updateUserCoupon(userCoupon.getId(), false); 
					double pourcentage = coupon.getPourcentage() / 100;
					newPrix = prix - (prix * pourcentage);
				}
			}
			return newPrix;
		}
		System.out.println("Aucun code trouve pour l'utilisateur. Le prix ne change pas");
		return prix;
	}
	
	public UserCoupon updateUserCoupon(int id, boolean valable) {
		if (userCouponRepository.findById(id).isPresent()) {
			UserCoupon existingUserCoupon = userCouponRepository.findById(id).get();
			existingUserCoupon.setValable(valable);
			return userCouponRepository.save(existingUserCoupon);
		} else
			return null;
	}

	public String deleteCoupon(int id) { 
		if (couponRepository.findById(id).isPresent()) {
			List<UserCoupon> userCoupons = userCouponRepository.getByCouponId(id, true); 
			for (UserCoupon userCoupon : userCoupons) {
				updateUserCoupon(userCoupon.getId(), false); 
			}
			couponRepository.deleteById(id);
			return "coupon et userCoupons supprimés";
		} else
			return "coupon et userCoupons non supprimés";
	}
	
	private boolean compareDatesBefore(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "MM-dd-yyyy" );
		LocalDate ld = LocalDate.parse(input, formatter );
		Date today = new Date();
		String todayString = sdf.format(today);
		LocalDate todayLocalDate = LocalDate.parse(todayString, formatter);
		return ld.isBefore(todayLocalDate);
	}
	
	private boolean compareDatesAfter(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "MM-dd-yyyy" );
		LocalDate ld = LocalDate.parse(input, formatter );
		Date today = new Date();
		String todayString = sdf.format(today);
		System.out.println(todayString);
		LocalDate todayLocalDate = LocalDate.parse(todayString, formatter);
		return ld.isAfter(todayLocalDate);
	}
}
