package io.jetbrains.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jetbrains.ratingsdataservice.model.Rating;
import io.jetbrains.ratingsdataservice.model.UserRating;
//localhost:8082/ratingsdata/movies/foo
@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource 
{
	//http://localhost:8082/ratingsdata/movies/foo
	 @RequestMapping("/movies/{movieId}")
	    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
	        return new Rating(movieId, 4);
	    }
	 //http://localhost:8082/ratingsdata/user/foo
	    @RequestMapping("/user/{userId}")
	    public UserRating getUserRatings(@PathVariable("userId") String userId) {
	        UserRating userRating = new UserRating();
	       // userRating.initData(userId);
	        return userRating;
	    }
	    //http://localhost:8082/ratingsdata/users/foo
	    @RequestMapping("/users/{userId}")
	    public UserRating getRatings(@PathVariable("userId") String userId) {
	    	List<Rating> ratings=Arrays.asList(
					new Rating("1234", 4),
					new Rating("5678", 3));
	    	UserRating userRating = new UserRating();
	        userRating.setUserRatings(ratings);
	        return userRating;
	    }
}
