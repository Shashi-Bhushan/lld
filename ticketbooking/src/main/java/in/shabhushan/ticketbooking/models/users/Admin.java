package in.shabhushan.ticketbooking.models.users;

import in.shabhushan.ticketbooking.models.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {
    @Column(name = "designation")
    private String designation;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "years_in_company")
    private Long yearsInCompany;

    @OneToOne
    private User user;
}
