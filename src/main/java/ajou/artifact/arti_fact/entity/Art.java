package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*; 
import lombok.*;

@Entity
@Table(name = "ART", indexes = {
    @Index(name = "idx_art_name", columnList = "Name"),
    @Index(name = "idx_art_genre", columnList = "Genre"),
    @Index(name = "idx_art_theme", columnList = "Theme"),
    @Index(name = "idx_art_age", columnList = "Age"),
    @Index(name = "idx_art_artist", columnList = "Artist_ID"),
    @Index(name = "idx_art_gallery", columnList = "Gallery_ID")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Art {

    @Id
    @Column(name = "Art_ID", nullable = false)
    private String artId;

    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Artist_ID", nullable = false) 
    private Artist artist;  // Artist 엔티티와 연관 (foreign key: Artist_ID)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Gallery_ID", nullable = false)
    private Gallery gallery; // Gallery 엔티티와 연관  (foreign key: Gallery_ID)

    @Column(name = "Display")
    private Boolean display; // 전시 여부 (1/0 -> true/false)

    @Column(name = "Genre")
    private String genre; 

    @Column(name = "Theme")
    private String theme; 

    @Column(name = "Age")
    private Integer age; 

    @Column(name = "image")
    private String imageUrl;
}