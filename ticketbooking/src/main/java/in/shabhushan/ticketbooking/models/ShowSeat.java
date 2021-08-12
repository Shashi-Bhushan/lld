package in.shabhushan.ticketbooking.models;

import javax.persistence.*;

@Entity
@Table(name = "show_seats")
public class ShowSeat extends BaseEntity {
    @Column(name = "seat_status")
    private String seatStatus;

    @Column(name = "price")
    private Integer price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id", nullable = false, unique = true)
    private Show show;
}
