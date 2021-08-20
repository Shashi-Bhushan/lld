package in.shabhushan.ticketbooking.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "show_seats")
public class ShowSeat extends BaseEntity {
    @Column(name = "seat_status")
    private String seatStatus;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false, unique = true)
    private Show show;

    @Column(name = "occupied")
    private boolean occupied;

    @OneToOne(
            mappedBy = "showSeat",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Booking booking;
}
