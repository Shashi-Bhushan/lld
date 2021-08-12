package in.shabhushan.ticketbooking.models.users;

import in.shabhushan.ticketbooking.models.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    private Set<Role> roles = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof User)) return false;
        if (obj == this) return true;
        return this.getId().equals(((User) obj).getId()) &&
                this.getUsername().equals(((User) obj).getUsername());
    }
}
