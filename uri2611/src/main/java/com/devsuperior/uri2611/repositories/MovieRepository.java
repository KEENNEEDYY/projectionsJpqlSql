package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.pojections.MovieMinProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query(nativeQuery = true, value = "SELECT movies.id, movies.name "
			+ "FROM movies "
			+ "INNER JOIN genres ON genres.id = movies.id_genres "
			+ "WHERE description = :genreName")
	public List<MovieMinProjection> search1(String genreName);
	
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) "
			+ "FROM Movie obj "
			+ "WHERE obj.genre.description = :genreName")
	public List<MovieMinDTO> search2(String genreName);

}
