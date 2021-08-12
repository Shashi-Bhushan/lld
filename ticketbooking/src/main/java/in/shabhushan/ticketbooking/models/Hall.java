package in.shabhushan.ticketbooking.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "halls")
public class Hall extends BaseEntity {

    @Column(name = "hall_number")
    private String hallNumber;

    @Column(name = "seat_count")
    private Integer seatCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(
            mappedBy = "hall",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(
            mappedBy = "hall",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Show> shows = new HashSet<>();
}
