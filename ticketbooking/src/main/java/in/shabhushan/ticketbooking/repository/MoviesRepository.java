package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {

    //public List<Movie> findByCity(City city);

    @Query(
            value =
                    "select * from movies m where m.name =:searchTerm",
            nativeQuery = true)
    public List<Movie> searchMovies(@Param("searchTerm") String searchTerm);
}
