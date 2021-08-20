package in.shabhushan.ticketbooking.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "halls",
        uniqueConstraints = @UniqueConstraint(columnNames = {"hall_number", "cinema_id"})
)
public class Hall extends BaseEntity {

    @Column(name = "hall_number")
    private Integer hallNumber;

    @Column(name = "seat_count")
    private Integer seatCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(
            mappedBy = "hall",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(
            mappedBy = "hall",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Show> shows = new HashSet<>();
}
