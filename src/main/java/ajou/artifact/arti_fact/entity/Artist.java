package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ARTIST")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Artist {

    @Id
    @Column(name = "Artist_ID", nullable = false)
    private String artistId;

    @Column(name = "Name", nullable = false)
    private String name; 
    
    @Column(name = "BirthDate")
    private Integer birthDate; 

    @Column(name = "DeadDate")
    private Integer deadDate; 

    @Column(name = "Nationality")
    private String nationality;

    @Column(name = "Theme")
    private String theme;

    @Column(name = "Info", columnDefinition = "TEXT") // 긴 설명이 들어갈 수 있으므로 TEXT 타입
    private String info; 

    // 한 명의 작가는 여러 작품을 가질 수 있음
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    @Builder.Default 
    private List<Art> arts = new ArrayList<>();
}