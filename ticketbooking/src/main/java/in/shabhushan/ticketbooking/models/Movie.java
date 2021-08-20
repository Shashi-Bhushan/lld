package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Movie extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "producer")
    private String producer;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private Date releaseDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "language")
    private Language language;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Show> shows = new HashSet<>();
}
