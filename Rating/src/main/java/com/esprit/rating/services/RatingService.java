package com.esprit.rating.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.esprit.rating.entities.Produit;
import com.esprit.rating.entities.Rating;
import com.esprit.rating.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	
	public void ajoutRating(Rating p) {
		ratingRepository.save(p);
	}
	
	public List<Rating> listRating() {
		return ratingRepository.findAll();
	}
	
	public String rateProduct(int id) {
			final String uri ="http://192.168.99.100:8762/stock-service/produit/"+id;
			RestTemplate resTemplate = new RestTemplate();
			Object rate = ratingRepository.getrateProduct(id);
			Produit result = resTemplate.getForObject(uri, Produit.class);
			System.out.println("Le produit :"+result.getNom()+" a le prix : "+result.getPrix()+" et un rating : "+rate);
		return "Le produit :"+result.getNom()+" a le prix : "+result.getPrix()+" et un rating : "+rate;
	}
}
