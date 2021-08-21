package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemasRepository extends JpaRepository<Cinema, Long>, JpaSpecificationExecutor<Cinema> {
}
