package in.shabhushan.lld.expense.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;

public class BaseEntity {
    private final int id;
    private final LocalDate createdAt;
    private LocalDate lastUpdated;

    public BaseEntity(int id) {
        this.id = id;
        this.lastUpdated = LocalDate.now();
        this.createdAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id && lastUpdated.equals(that.lastUpdated) && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastUpdated, createdAt);
    }
}
