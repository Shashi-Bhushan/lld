package in.shabhushan.ticketbooking.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cinemas")
@Data
public class Cinema extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(
            mappedBy = "cinema",
            // The mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Hall> halls = new HashSet<>();
}
