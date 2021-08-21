package in.shabhushan.ticketbooking.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"movie"}) @NoArgsConstructor
@Entity
@Table(name = "shows")
public class Show extends BaseEntity {
    @Column(name = "start_time")
    private Date startTime; // include Timezone

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(name = "cancelled", columnDefinition = "bit(1) default 0")
    private Boolean cancelled;

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ShowSeat> showSeat = new HashSet<>();

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    /**
     * Can be booked if
     * - Show has not been cancelled
     * - Show has not yet started
     */
    public boolean isNotStartedOrCancelled() {
        return !cancelled && startTime.after(new Date());
    }
}
