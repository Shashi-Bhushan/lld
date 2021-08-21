package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Seat;
import in.shabhushan.ticketbooking.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Long>, JpaSpecificationExecutor<Seat> {
}
