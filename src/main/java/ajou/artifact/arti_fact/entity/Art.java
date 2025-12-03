package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*; 
import lombok.*;

@Entity
@Table(name = "ART")
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