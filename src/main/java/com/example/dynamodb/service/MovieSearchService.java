package com.example.dynamodb.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.example.dto.Movie;
import  com.example.dynamdodb.repositories.MovieRepository;


@Service
public class MovieSearchService {
	  @Autowired
	  private AmazonDynamoDB amazondynamoDB ;
	  
	  
	  
	  private com.example.dynamdodb.repositories.MovieRepository movieRepository;

	    public MovieSearchService(MovieRepository MovieRepository) {
	        this.movieRepository = MovieRepository;
	    }

	    public List<Movie> findAllMovies() {
	        return StreamSupport.stream(movieRepository.findAll().spliterator(), true).collect(Collectors.toList());
	    }

	    
	    
		public void addNewMovieThroughDynamo() {

		DynamoDB dynamoDB = new DynamoDB(amazondynamoDB);
		Table table = dynamoDB.getTable("movie");

		String title = "AWS Training Movie";

		try {

			System.out.println("Starting the insert process");

			PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("filmId", "3")
					.with("Plot", "Nothing Happens this is a test").with("Rated", "UnApproved").with("Title", title));
			System.out.println("PutItem Succeded : "+outcome.getPutItemResult());
			System.out.println("Successfully inserted");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
		}

		public void addNewMovie(Movie movie) {
			
			movieRepository.save(movie);
			
			
		}

		
	 
	
	    
	    
	
}