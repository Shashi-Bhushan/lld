package in.shabhushan.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "messages", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "uuid" })
})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    @NonNull
    private String text;

    @Column(name = "uuid", columnDefinition = "char(36)")
    @Type(type="uuid-char")
    private UUID uuid;

    @OneToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name="nextMessage_id")
    private Message nextMessage;

    @OneToOne(mappedBy="nextMessage", cascade = CascadeType.ALL)
    private Message currentMessage;
}
