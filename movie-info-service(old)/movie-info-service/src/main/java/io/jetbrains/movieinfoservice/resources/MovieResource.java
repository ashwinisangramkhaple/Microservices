package io.jetbrains.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jetbrains.movieinfoservice.model.Movie;
//localhost:8081/movies/foo
@RestController
@RequestMapping("/movies")
public class MovieResource 
{
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId)
	{
		Movie m=new Movie();
		m.setMovieId("101");
		m.setName("test name");
		return m;
	}
}
