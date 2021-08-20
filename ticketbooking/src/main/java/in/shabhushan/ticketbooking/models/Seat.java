package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.SeatType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seats")
public class Seat extends BaseEntity {

    @Column(name = "seat_location")
    private Integer seatLocation;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "seat_type")
    private SeatType seatType;

    @Column(name = "is_undermaintainance")
    private boolean isUnderMaintainance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @OneToMany(
            mappedBy = "seat",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<ShowSeat> showSeat = new HashSet<>();
}
