package in.shabhushan.ticketbooking.models.users;

import in.shabhushan.ticketbooking.models.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String description;
}
