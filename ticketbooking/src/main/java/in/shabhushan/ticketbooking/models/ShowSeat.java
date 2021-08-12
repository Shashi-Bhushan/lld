package in.shabhushan.ticketbooking.models;

import javax.persistence.*;

@Entity
@Table(name = "show_seats")
public class ShowSeat extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hallseat_id", nullable = false)
    private Seat hallSeat;

//    @OneToOne(optional=false, mappedBy="showSeat")
//    private Show show;
}
