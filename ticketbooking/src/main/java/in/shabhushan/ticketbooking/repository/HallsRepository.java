package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallsRepository extends JpaRepository<Hall, Long>, JpaSpecificationExecutor<Hall> {
    @Query(
            value = "SELECT halls.* FROM halls WHERE hall_number = :hallNumber AND cinema_id = :cinemaId",
            nativeQuery = true
    )
    public List<Hall> getByHallNumberAndCinema(Long hallNumber, Long cinemaId);
}
