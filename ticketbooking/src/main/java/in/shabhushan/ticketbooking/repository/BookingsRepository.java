package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
}
