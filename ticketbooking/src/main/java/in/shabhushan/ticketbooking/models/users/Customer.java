package in.shabhushan.ticketbooking.models.users;

import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.enums.State;
import in.shabhushan.ticketbooking.models.BaseEntity;
import in.shabhushan.ticketbooking.models.Booking;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "city")
    private City city;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private State state;

    @Column(name = "email")
    private String email;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Booking> bookings = new HashSet<>();

    @OneToOne
    private User user;
}
