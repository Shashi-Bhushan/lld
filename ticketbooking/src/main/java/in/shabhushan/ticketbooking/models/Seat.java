package in.shabhushan.ticketbooking.models;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat extends BaseEntity {

    @Column(name = "seat_location")
    private String seatLocation;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "is_undermaintainance")
    private boolean isUnderMaintainance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

//    @OneToOne(optional=false, mappedBy="showSeat")
//    private ShowSeat showSeat;
}
