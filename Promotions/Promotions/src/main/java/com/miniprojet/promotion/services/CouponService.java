package com.miniprojet.promotion.services;

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

import com.miniprojet.promotion.entities.Coupon;
import com.miniprojet.promotion.entities.UserCoupon;
import com.miniprojet.promotion.repos.CouponRepository;
import com.miniprojet.promotion.repos.UserCouponRepository;

@Service
public class CouponService {
	private static final DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private UserCouponRepository userCouponRepository;
	
	public String ajouterCoupon(Coupon coupon) {
		if (couponRepository.rechercheCode(coupon.getCode()).size() > 0) { // check si le coupon existe et est disponible
			return "Ce coupon existe, veuillez changer de code promo";
		}
		couponRepository.save(coupon);
		return "Coupon ajouté";
	}
	
	public List<Coupon> listeCouponsUtiliseables() { // liste les coupons avec etat true, et ayant des dates encore disponibles
		List<Coupon> coupons = couponRepository.getCouponsEtat(true); // fonction ajoutée dans le repo pour l'etat
		List<Coupon> result = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			boolean from = compareDatesBefore(coupon.getDate_debut()); // fonction private pour comparer les dates
			boolean to = compareDatesAfter(coupon.getDate_fin());
			if (from && to) {
				result.add(coupon); // on ajoute le coupon qui verifie ces conditions dans l'array result
			}
		}
		
		return result;
	}
	
	public Coupon rechercherCode(String code) {
		List<Coupon> listeCoupons = couponRepository.rechercheCode(code); // recherche le code et le coupon avec etat true
		if (listeCoupons.size() > 0 && listeCoupons.get(0) != null) {
			return listeCoupons.get(0);
		}
		return null;
	}
	
	public UserCoupon utiliserCode(int userId, String code) {
		// on peut utiliser le code même si la date a été depassée. La verification se fera dans le calcul du prix
		Coupon coupon = rechercherCode(code);
		if (coupon != null && !(userCouponRepository.userCouponExiste(userId, coupon.getId()).size() > 0)) { // on recherche le coupon et l'existance de userCoupon
			// on ajoute le code promo
			UserCoupon userCoupon = new UserCoupon(userId, coupon.getId(), true);
			userCoupon.setValable(true);
			return userCouponRepository.save(userCoupon);
		}
		// sinon rien
		System.out.println("ce code n'existe pas ou est dejà utilise par l'utilisateur");
		return null;
	}
	
	public double calculerPrix(int userId, double prix) { // applique les pourcentages de tous les coupons de l'utilisateur
		// on recherche un code promo pour l'utilisateur
		List<UserCoupon> userCoupons = userCouponRepository.getUserCoupons(userId);
		double newPrix = prix;
		if (userCoupons.size() > 0) {
			for (UserCoupon userCoupon : userCoupons) {
				Coupon coupon = couponRepository.findById(userCoupon.getCouponId()).get(); // Il faudrait normalement ajouter une verification si le userCoupon existe avant le get() (au cas où le coupon a été supprimé) 
				// On verifie si le coupon est utilisable avant le calcul
				boolean from = compareDatesBefore(coupon.getDate_debut()); // fonction private pour comparer les dates
				boolean to = compareDatesAfter(coupon.getDate_fin());
				if (from && to && userCoupon.isValable()) { // le check de isValable peut se faire dans le repository mais b5elt hhh
					updateUserCoupon(userCoupon.getId(), false); // le userCoupon n'est plus valable une fois utilisé
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

	public String deleteCoupon(int id) { // supprime un coupon et tous les userCoupons relies
		if (couponRepository.findById(id).isPresent()) {
			List<UserCoupon> userCoupons = userCouponRepository.getByCouponId(id, true); // we get tous les userCoupons valables et ayant le couponId (valable peut être changé pour afficher les userCoupons non valables dans une autre fonction)
			for (UserCoupon userCoupon : userCoupons) {
				updateUserCoupon(userCoupon.getId(), false); // rendre les userCoupons non valables au lieu de supprimer (attention, peut generer une erreur dans une autre fonction when we want to get le coupon lié, parce qu'il sera supprimé)
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
