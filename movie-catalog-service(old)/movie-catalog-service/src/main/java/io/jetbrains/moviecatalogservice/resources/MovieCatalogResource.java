package io.jetbrains.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.jetbrains.moviecatalogservice.models.CatalogItem;
import io.jetbrains.moviecatalogservice.models.Movie;
import io.jetbrains.moviecatalogservice.models.Rating;
import io.jetbrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource 
{
	@Autowired
	private RestTemplate restTemplate; //gives Synchronous call.
	//  OR
	@Autowired
	private WebClient.Builder webClientBuilder; //gives Asynchronous call.
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId)
	{
		
		WebClient.Builder builder=WebClient.builder();
		
		//get all rated movies
		//for each movie id call movie info service and get details
		//put them all together
		List<Rating> ratings=Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3));
		UserRating ratings1=restTemplate.getForObject("http://localhost:8082/ratingsdata/users/"+userId, UserRating.class);
		
		return ratings1.getUserRatings().stream().map(rating-> {
			//for each movie id call movie info service and get details
			Movie movie=restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
			//put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());
			/*
			//rest template is making a call to the api and unmarshal(converting xml content to java object) the object
			Movie movie=restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class); //Synchronous call.
			//OR
			Movie movie=webClientBuilder
					.build()
					.get()
					.uri("http://localhost:8081/movies/"+rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();	
			//Asynchronous object. whatever body you get back convert it into Movie instance
			
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());//return object given by rest template
			}).collect(Collectors.toList());
		
		*/
		
		//List<CatalogItem> ls=new ArrayList<>();
		//CatalogItem ci=new CatalogItem("Transformers", "test", 4);
		//ls.add(ci);
		//return ls;
	}
}
