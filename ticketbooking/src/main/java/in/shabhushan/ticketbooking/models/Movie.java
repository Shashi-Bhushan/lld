package in.shabhushan.ticketbooking.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Show> shows = new HashSet<>();
}
