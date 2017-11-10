package br.ufc.tabd.dao;

import java.util.List;

import br.ufc.tabd.model.*;

public class teste {

	public static void main(String[] args) {
		
		RatingsDAO t = new RatingsDAO();
		List<Ratings> movies = t.getRatings();
		
		for (Ratings m : movies) {
			System.out.println(m.getRating() + m.getUserid());
		}

	}

}
