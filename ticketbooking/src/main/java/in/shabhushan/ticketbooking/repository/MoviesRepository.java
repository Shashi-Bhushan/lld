package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    List<Movie> findByName(String name);

    @Query(
            value =
                    "select * from movies m where m.name =:searchTerm",
            nativeQuery = true)
    public List<Movie> searchMovies(@Param("searchTerm") String searchTerm);

    @Query(
            value =
                    "SELECT movies.* FROM cinemas " +
                            "INNER JOIN halls ON cinemas.id = halls.cinema_id " +
                            "INNER JOIN shows ON halls.id = shows.hall_id " +
                            "INNER JOIN movies ON movies.id = shows.movie_id " +
                            "INNER JOIN addresses ON cinemas.id = addresses.cinema_id " +
                            "WHERE addresses.city = :city AND movies.name =:movieName",
            nativeQuery = true)
    List<Movie> getMoviesByCity(String city, String movieName);
}
