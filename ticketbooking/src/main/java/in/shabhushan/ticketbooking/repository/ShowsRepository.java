package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowsRepository extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show> {

    @Query(
            value = "SELECT shows.* FROM shows WHERE shows.movie_id = :movie AND shows.hall_id = :hall",
            nativeQuery = true
    )
    List<Show> getShowByMovieAndHall(Long movie, Long hall);

    @Query(
            value =
                    "SELECT shows.* FROM shows " +
                            "INNER JOIN movies ON movies.id = shows.movie_id " +
                            "INNER JOIN halls ON halls.id = shows.hall_id " +
                            "INNER JOIN cinemas ON cinemas.id = halls.cinema_id " +
                            "INNER JOIN addresses ON cinemas.id = addresses.cinema_id " +
                            "WHERE addresses.city = :city AND movies.name =:movieName",
            nativeQuery = true
    )
    List<Show> getShowByMovieAndCity(String city, String movieName);
}
