package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeat, Long>, JpaSpecificationExecutor<ShowSeat> {
}
