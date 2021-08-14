package in.shabhushan.ticketbooking.models;

import in.shabhushan.ticketbooking.enums.City;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cinemas")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cinema extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;

    @OneToMany(
            mappedBy = "cinema",
            // The mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Hall> halls = new HashSet<>();
}
