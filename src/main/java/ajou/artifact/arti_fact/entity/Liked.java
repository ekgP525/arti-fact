package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "liked")
@Getter
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    public Liked() {}

    public Liked(Users user, Art art) {
        this.user = user;
        this.art = art;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Timestamp.from(Instant.now());
    }
}
