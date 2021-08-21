package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HallsRepository extends JpaRepository<Hall, Long>, JpaSpecificationExecutor<Hall> {
}
