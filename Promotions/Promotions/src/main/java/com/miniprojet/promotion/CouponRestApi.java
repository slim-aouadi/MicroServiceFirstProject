package com.miniprojet.promotion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/coupons")
public class CouponRestApi {
	@Autowired
	private CouponService couponService;
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> addItem(@RequestBody Coupon coupon) {
		return new ResponseEntity<>(couponService.ajouterCoupon(coupon), HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Coupon>> listeCoupons() {
		return new ResponseEntity<>(couponService.listeCouponsUtiliseables(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Coupon> getCoupon(@PathVariable(value = "code") String code) {
		return new ResponseEntity<>(couponService.rechercherCode(code), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{userId}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserCoupon> utiliserCode(@PathVariable(value = "userId") int userId, @PathVariable(value = "code") String code) {
		return new ResponseEntity<>(couponService.utiliserCode(userId, code), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}/{prix}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Double> calculerPrix(@PathVariable(value = "userId") int userId, @PathVariable(value = "prix") double prix) {
		return new ResponseEntity<>(couponService.calculerPrix(userId, prix), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCoupon(@PathVariable(value = "id") int id){
		return new ResponseEntity<>(couponService.deleteCoupon(id), HttpStatus.OK);
	}
}