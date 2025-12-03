package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GALLERY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Gallery {

    @Id
    @Column(name = "Gallery_ID", nullable = false)
    private String galleryId; 

    @Column(name = "Name", nullable = false)
    private String name; 

    @Column(name = "Address")
    private String address; 

    @Column(name = "OpenTime")
    private String openTime; 

    @Column(name = "ClosedTime")
    private String closedTime;

    @Column(name = "Fee")
    private Integer fee; 

    @Column(name = "Phone")
    private String phone; 

    // Gallery(1) : Art(N)
    @OneToMany(mappedBy = "gallery", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Art> arts = new ArrayList<>();
}