package in.shabhushan.ticketbooking.service.api;

import in.shabhushan.ticketbooking.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
