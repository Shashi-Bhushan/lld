package in.shabhushan.ticketbooking.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show extends BaseEntity {
    @Column(name = "start_time")
    private Date startTime; // include Timezone

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(name = "cancelled")
    private boolean cancelled;

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<ShowSeat> showSeat = new HashSet<>();

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
