package in.shabhushan.ticketbooking.models;

import javax.persistence.*;
import java.util.Date;

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


//    @OneToOne(
//            mappedBy = "show",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            orphanRemoval = true
//    )
//    private ShowSeat showSeat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
