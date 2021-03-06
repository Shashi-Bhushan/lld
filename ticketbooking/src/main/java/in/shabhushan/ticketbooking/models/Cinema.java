package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cinemas")
@SecondaryTable(
        name = "addresses",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "cinema_id", referencedColumnName = "id")
)
public class Cinema extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "street", table = "addresses", columnDefinition = "varchar(50) not null")
    private String street;

    // This is not efficient, in prod like system, should use ordinal
    @Enumerated(EnumType.STRING)
    @Column(name = "city", table = "addresses")
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", table = "addresses")
    private State state;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "cinema",
            // The mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Hall> halls = new HashSet<>();
}
