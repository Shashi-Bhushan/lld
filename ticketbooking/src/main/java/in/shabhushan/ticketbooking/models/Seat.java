package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "seat",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ShowSeat> showSeat = new HashSet<>();
}
