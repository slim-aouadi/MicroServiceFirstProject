package com.esprit.rating.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.rating.entities.Rating;
import com.esprit.rating.services.RatingService;

@RestController
@RequestMapping(value="/rating")
public class RatingRestApi {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping(value = "/addRatingtoProduit")
	@ResponseStatus(HttpStatus.OK)
	public String addRating(@RequestBody Rating p) {
		ratingService.ajoutRating(p);
		return "Success";
	}
	
	@GetMapping(value = "/listRating")
	@ResponseStatus(HttpStatus.OK)
	public List<Rating> listRating() {
		return ratingService.listRating();
		
	}
	
	@GetMapping(value = "/rateProduct/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Object rateProduct(@PathVariable(value="id") int id) {
		return ratingService.rateProduct(id);
		
	}
	
	
	
	
	
}
