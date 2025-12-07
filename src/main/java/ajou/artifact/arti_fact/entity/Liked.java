package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LIKED", indexes = {
    @Index(name = "idx_liked_user_art", columnList = "User_ID,Art_ID", unique = true),
    @Index(name = "idx_liked_user", columnList = "User_ID"),
    @Index(name = "idx_liked_art", columnList = "Art_ID")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Liked_ID")
    private Long likedId;

    // User와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    // Art와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Art_ID", nullable = false)
    private Art art;

    // User와 Art를 받는 생성자
    public Liked(User user, Art art) {
        this.user = user;
        this.art = art;
    }
}