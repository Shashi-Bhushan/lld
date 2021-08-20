package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.BookingStatus;
import in.shabhushan.ticketbooking.models.users.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private BookingStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showSeat_id", nullable = false)
    private ShowSeat showSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
}
